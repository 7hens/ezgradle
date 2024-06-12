package me.thens.ezgradle.dependency.data

import groovy.json.JsonSlurper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.thens.ezgradle.dependency.bom.ArtifactKey
import me.thens.ezgradle.util.asList
import me.thens.ezgradle.util.asMap
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object MavenSearch : RepositoryRepo {
    private const val BASE_URL = "https://search.maven.org"

    override suspend fun queryLatestVersion(group: String, name: String): String? {
        return withContext(Dispatchers.IO) {
            val url = "$BASE_URL/solrsearch/select?q=g:$group+AND+a:$name&rows=20&wt=json"
            val httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(url))
                .header("Content-Type", "application/json")
                .build()
            val httpClient = HttpClient.newHttpClient()
            val response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
            val jsonSlurper = JsonSlurper()
            val responseData = jsonSlurper.parseText(response.body())
            println("Response Data: $responseData")
            getLatestVersion(responseData)
        }
    }

    private fun getLatestVersion(responseData: Any?): String? {
        val list = responseData?.asMap()
            ?.get("response")?.asMap()
            ?.get("docs")?.asList()
            ?: return null
        val versions = list.mapNotNull { it?.asMap()?.get("latestVersion")?.toString() }
        return versions.firstOrNull { !(it.contains("-alpha") || it.contains("-SNAPSHOT")) }
            ?: versions.firstOrNull()
    }

    override suspend fun queryDependencies(artifactKey: ArtifactKey): List<ArtifactKey> {
        return emptyList()
    }
}
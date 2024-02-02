package me.thens.ezgradle

import groovy.json.JsonSlurper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object MavenSearch {
    private const val BASE_URL = "https://search.maven.org"

    suspend fun queryLatestVersion(group: String, name: String): String? {
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
            responseData.asMap()?.get("response")?.asMap()?.get("docs")?.asList()?.let { list ->
                val versions = list.mapNotNull { it?.asMap()?.get("latestVersion")?.toString() }
                versions.firstOrNull { !(it.contains("-alpha") || it.contains("-SNAPSHOT")) }
                    ?: versions.firstOrNull()
            }
        }
    }
}
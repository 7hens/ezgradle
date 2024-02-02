package me.thens.ezgradle

data class RepoCoordinate(
    val group: String,
    val name: String,
    val version: String,
) {
    override fun toString(): String {
        return "$group:$name:$version"
    }

    companion object
}

package com.mr3y.podcastindex

fun PodcastIndexClient(config: PodcastIndexClientConfig.() -> Unit): PodcastIndexClient {
    return PodcastIndexClient()
}

class PodcastIndexClient {

    companion object {
        internal const val BaseUrl = "https://api.podcastindex.org/api/1.0"
    }
}
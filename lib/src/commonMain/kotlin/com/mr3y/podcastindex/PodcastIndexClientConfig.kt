package com.mr3y.podcastindex

class PodcastIndexClientConfig {

    fun authentication(action: Authentication.() -> Unit) {

    }
}

object Authentication {
    var userAgent: String? = null

    var authKey: String? = null

    var authSecret: String? = null
}

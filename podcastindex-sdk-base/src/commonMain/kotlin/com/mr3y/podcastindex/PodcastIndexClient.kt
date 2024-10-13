package com.mr3y.podcastindex

import androidx.annotation.RestrictTo
import com.mr3y.podcastindex.services.Episodes
import com.mr3y.podcastindex.services.Misc
import com.mr3y.podcastindex.services.Podcasts
import com.mr3y.podcastindex.services.Recent
import com.mr3y.podcastindex.services.Search
import com.mr3y.podcastindex.services.Value
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

/**
 * PodcastIndexClient instance responsible for interacting with different PodcastIndex API endpoints.
 */
public class PodcastIndexClient
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
constructor(
    private val httpClientConfig: HttpClientConfig<*>.() -> Unit,
) {

    private val client: HttpClient = HttpClient(defaultHttpClientEngineFactory()) {
        httpClientConfig()
    }

    public val podcasts: Podcasts = Podcasts(client)

    public val search: Search = Search(client)

    public val episodes: Episodes = Episodes(client)

    public val recent: Recent = Recent(client)

    public val value: Value = Value(client)

    public val misc: Misc = Misc(client)

    internal companion object {
        internal const val BaseUrl = "https://api.podcastindex.org/api/1.0"
    }
}

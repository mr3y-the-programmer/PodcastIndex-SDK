package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.extensions.parameterBoolean
import com.mr3y.podcastindex.extensions.parameterLimit
import com.mr3y.podcastindex.extensions.parameterList
import com.mr3y.podcastindex.extensions.withErrorHandling
import com.mr3y.podcastindex.model.Category
import com.mr3y.podcastindex.model.MultipleEpisodesResult
import com.mr3y.podcastindex.model.RecentFeedsResult
import com.mr3y.podcastindex.model.RecentNewFeedsResult
import com.mr3y.podcastindex.model.SoundbitesResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.datetime.Instant
import kotlin.jvm.JvmInline

public class Recent internal constructor(private val client: HttpClient) {

    public suspend fun episodes(
        ignoreContainsWord: String? = null,
        offset: Offset? = null,
        limit: Int = 0,
        includeFullText: Boolean = false,
    ): MultipleEpisodesResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/recent/episodes") {
            parameter("excludeString", ignoreContainsWord)
            parameter(
                "before",
                offset?.let {
                    when (it) {
                        is Offset.Id -> it.beforeId
                        is Offset.Time -> it.before.toEpochMilliseconds()
                    }
                },
            )
            parameterLimit(limit)
            parameterBoolean("fulltext", includeFullText)
        }
    }

    public suspend fun feeds(
        limit: Int = 0,
        since: Instant? = null,
        languages: List<LanguageTag> = emptyList(),
        includeCategories: List<Category> = emptyList(),
        excludeCategories: List<Category> = emptyList(),
    ): RecentFeedsResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/recent/feeds") {
            parameterLimit(limit)
            parameter("since", since?.toEpochMilliseconds())
            parameterList("lang", languages)
            parameterList("cat", includeCategories, transform = { it.id.toString() })
            parameterList("notcat", excludeCategories, transform = { it.id.toString() })
        }
    }

    public suspend fun newFeeds(
        limit: Int = 0,
        since: Instant? = null,
        feedId: Long? = null,
        descending: Boolean = false,
    ): RecentNewFeedsResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/recent/newfeeds") {
            parameterLimit(limit)
            parameter("since", since?.toEpochMilliseconds())
            parameter("feedid", feedId)
            parameterBoolean("desc", descending)
        }
    }

    public suspend fun newValueFeeds(
        limit: Int = 0,
        since: Instant? = null,
    ): RecentFeedsResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/recent/newvaluefeeds") {
            parameterLimit(limit)
            parameter("since", since?.toEpochMilliseconds())
        }
    }

    public suspend fun soundbites(
        limit: Int = 0,
    ): SoundbitesResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/recent/soundbites") {
            parameterLimit(limit)
        }
    }
}

public sealed interface Offset {

    @JvmInline
    public value class Id(public val beforeId: Long) : Offset

    @JvmInline
    public value class Time(public val before: Instant) : Offset
}

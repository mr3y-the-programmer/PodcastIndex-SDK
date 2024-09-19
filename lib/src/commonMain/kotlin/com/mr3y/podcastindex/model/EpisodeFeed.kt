package com.mr3y.podcastindex.model

import com.mr3y.podcastindex.extensions.EpisodeStatusSerializer
import com.mr3y.podcastindex.extensions.EpisodeTypeSerializer
import com.mr3y.podcastindex.extensions.ExplicitSerializer
import com.mr3y.podcastindex.extensions.InstantSerializer
import dev.drewhamilton.poko.Poko
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Poko
public class MultipleEpisodesResult(
    @SerialName(value = "liveItems") public val liveItems: List<LiveEpisodeFeed>? = null,
    @SerialName(value = "items") public val items: List<EpisodeFeed>
)

@Serializable
@Poko
public class SingleEpisodeResult(
    @SerialName(value = "episode") public val episode: EpisodeFeed
)

@Serializable
@Poko
public class LiveEpisodeFeed(
    @SerialName(value = "id") public val id: Long,
    @SerialName(value = "title") public val title: String,
    @SerialName(value = "link") public val link: String,
    @SerialName(value = "description") public val description: String,
    @SerialName(value = "guid") public val guid: String,
    @SerialName(value = "datePublished") @Serializable(InstantSerializer::class) public val datePublished: Instant,
    @SerialName(value = "datePublishedPretty") public val datePublishedPretty: String,
    @SerialName(value = "dateCrawled") @Serializable(InstantSerializer::class) public val dateCrawled: Instant,
    @SerialName(value = "enclosureUrl") public val enclosureUrl: String,
    @SerialName(value = "enclosureType") public val enclosureType: String,
    @SerialName(value = "enclosureLength") public val enclosureLength: Int,
    @SerialName(value = "startTime") @Serializable(InstantSerializer::class) public val startTime: Instant,
    @SerialName(value = "endTime") @Serializable(InstantSerializer::class) public val endTime: Instant,
    @SerialName(value = "status") @Serializable(EpisodeStatusSerializer::class) public val status: Status,
    @SerialName(value = "contentLink") public val contentLink: String? = null,
    @SerialName(value = "duration") public val duration: Int? = null,
    @SerialName(value = "explicit") @Serializable(ExplicitSerializer::class) public val explicit: Explicit,
    @SerialName(value = "episode") public val episode: Int? = null,
    @SerialName(value = "episodeType") @Serializable(EpisodeTypeSerializer::class) public val episodeType: EpisodeType? = null,
    @SerialName(value = "season") public val season: Int? = null,
    @SerialName(value = "image") public val image: String,
    @SerialName(value = "feedItunesId") public val feedItunesId: Int? = null,
    @SerialName(value = "feedImage") public val feedImage: String,
    @SerialName(value = "feedId") public val feedId: Int,
    @SerialName(value = "feedLanguage") public val feedLanguage: String,
    @SerialName(value = "feedDuplicateOf") public val feedDuplicateOf: Int? = null,
    @SerialName(value = "chaptersUrl") public val chaptersUrl: String? = null,
    @SerialName(value = "transcriptUrl") public val transcriptUrl: String? = null
)

@Serializable
@Poko
public class EpisodeFeed(
    @SerialName(value = "id") public val id: Long,
    @SerialName(value = "title") public val title: String,
    @SerialName(value = "link") public val link: String,
    @SerialName(value = "description") public val description: String? = null,
    @SerialName(value = "guid") public val guid: String,
    @SerialName(value = "datePublished") @Serializable(InstantSerializer::class) public val datePublished: Instant,
    @SerialName(value = "dateCrawled") @Serializable(InstantSerializer::class) public val dateCrawled: Instant,
    @SerialName(value = "enclosureUrl") public val enclosureUrl: String,
    @SerialName(value = "enclosureType") public val enclosureType: String,
    @SerialName(value = "enclosureLength") public val enclosureLength: Int,
    @SerialName(value = "duration") public val duration: Int? = null,
    @SerialName(value = "explicit") @Serializable(ExplicitSerializer::class) public val explicit: Explicit,
    @SerialName(value = "episode") public val episode: Int? = null,
    @SerialName(value = "episodeType") @Serializable(EpisodeTypeSerializer::class) public val episodeType: EpisodeType? = null,
    @SerialName(value = "season") public val season: Int? = null,
    @SerialName(value = "image") public val image: String,
    @SerialName(value = "feedItunesId") public val feedItunesId: Int? = null,
    @SerialName(value = "feedImage") public val feedImage: String,
    @SerialName(value = "feedId") public val feedId: Int,
    @SerialName(value = "feedUrl") public val feedUrl: String? = null,
    @SerialName(value = "feedAuthor") public val feedAuthor: String? = null,
    @SerialName(value = "feedTitle") public val feedTitle: String? = null,
    @SerialName(value = "feedLanguage") public val feedLanguage: String,
    @SerialName(value = "chaptersUrl") public val chaptersUrl: String? = null,
    @SerialName(value = "transcriptUrl") public val transcriptUrl: String? = null,
)

public enum class Status {
    Ended,
    Live
}

public enum class Explicit(public val code: Int) {
    No(0),
    Yes(1)
}

public enum class EpisodeType {
    Full,
    Trailer,
    Bonus
}

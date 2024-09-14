package com.mr3y.podcastindex.model

import com.mr3y.podcastindex.extensions.EpisodeTypeSerializer
import com.mr3y.podcastindex.extensions.ExplicitSerializer
import com.mr3y.podcastindex.extensions.InstantSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MultipleEpisodesResult(
    @SerialName(value = "feeds") val feeds: List<EpisodeFeed>
)

@Serializable
class EpisodeFeed(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "title") val title: String,
    @SerialName(value = "link") val link: String,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "guid") val guid: String,
    @SerialName(value = "datePublished") @Serializable(InstantSerializer::class) val datePublished: Instant,
    @SerialName(value = "dateCrawled") @Serializable(InstantSerializer::class) val dateCrawled: Instant,
    @SerialName(value = "enclosureUrl") val enclosureUrl: String,
    @SerialName(value = "enclosureType") val enclosureType: String,
    @SerialName(value = "enclosureLength") val enclosureLength: Int,
    @SerialName(value = "duration") val duration: Int? = null,
    @SerialName(value = "explicit") @Serializable(ExplicitSerializer::class) val explicit: Explicit,
    @SerialName(value = "episode") val episode: Int? = null,
    @SerialName(value = "episodeType") @Serializable(EpisodeTypeSerializer::class) val episodeType: EpisodeType? = null,
    @SerialName(value = "season") val season: Int? = null,
    @SerialName(value = "image") val image: String,
    @SerialName(value = "feedItunesId") val feedItunesId: Int? = null,
    @SerialName(value = "feedImage") val feedImage: String,
    @SerialName(value = "feedId") val feedId: Int,
    @SerialName(value = "feedUrl") val feedUrl: String,
    @SerialName(value = "feedAuthor") val feedAuthor: String,
    @SerialName(value = "feedTitle") val feedTitle: String,
    @SerialName(value = "feedLanguage") val feedLanguage: String,
    @SerialName(value = "chaptersUrl") val chaptersUrl: String? = null,
    @SerialName(value = "transcriptUrl") val transcriptUrl: String? = null,
)

enum class Explicit(val code: Int) {
    No(0),
    Yes(1)
}

enum class EpisodeType {
    Full,
    Trailer,
    Bonus
}

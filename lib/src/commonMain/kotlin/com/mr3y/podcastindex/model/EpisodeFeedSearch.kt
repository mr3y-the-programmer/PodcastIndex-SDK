package com.mr3y.podcastindex.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EpisodeFeedSearch(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "title") val title: String,
    @SerialName(value = "link") val link: String,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "guid") val guid: String,
    @SerialName(value = "datePublished") val datePublished: Instant,
    @SerialName(value = "dateCrawled") val dateCrawled: Instant,
    @SerialName(value = "enclosureUrl") val enclosureUrl: String,
    @SerialName(value = "enclosureType") val enclosureType: String,
    @SerialName(value = "enclosureLength") val enclosureLength: Int,
    @SerialName(value = "duration") val duration: Int? = null,
    @SerialName(value = "explicit") val explicit: Explicit,
    @SerialName(value = "episode") val episode: Int? = null,
    @SerialName(value = "episodeType") val episodeType: EpisodeType? = null,
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

enum class EpisodeType(val encodedValue: String) {
    Full("full"),
    Trailer("trailer"),
    Bonus("bonus")
}

package com.mr3y.podcastindex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EpisodeFeedSearch(
    @SerialName(value = "id") val id: Int? = null,
    @SerialName(value = "title") val title: String? = null,
    @SerialName(value = "link") val link: String? = null,
    @SerialName(value = "description") val description: String? = null,
    @SerialName(value = "guid") val guid: String? = null,
    @SerialName(value = "datePublished") val datePublished: Int? = null,
    @SerialName(value = "dateCrawled") val dateCrawled: Int? = null,
    @SerialName(value = "enclosureUrl") val enclosureUrl: String? = null,
    @SerialName(value = "enclosureType") val enclosureType: String? = null,
    @SerialName(value = "enclosureLength") val enclosureLength: Int? = null,
    @SerialName(value = "duration") val duration: Int? = null,
    @SerialName(value = "explicit") val explicit: Explicit? = null,
    @SerialName(value = "episode") val episode: Int? = null,
    @SerialName(value = "episodeType") val episodeType: EpisodeType? = null,
    @SerialName(value = "season") val season: Int? = null,
    @SerialName(value = "image") val image: String? = null,
    @SerialName(value = "feedItunesId") val feedItunesId: Int? = null,
    @SerialName(value = "feedImage") val feedImage: String? = null,
    @SerialName(value = "feedId") val feedId: Int? = null,
    @SerialName(value = "feedUrl") val feedUrl: String? = null,
    @SerialName(value = "feedAuthor") val feedAuthor: String? = null,
    @SerialName(value = "feedTitle") val feedTitle: String? = null,
    @SerialName(value = "feedLanguage") val feedLanguage: String? = null,
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

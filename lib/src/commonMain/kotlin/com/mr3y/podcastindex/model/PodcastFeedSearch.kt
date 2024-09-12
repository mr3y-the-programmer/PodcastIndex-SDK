package com.mr3y.podcastindex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PodcastFeedSearch (
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "podcastGuid") val podcastGuid: String,
    @SerialName(value = "title") val title: String,
    @SerialName(value = "url") val url: String,
    @SerialName(value = "originalUrl") val originalUrl: String? = null,
    @SerialName(value = "link") val link: String? = null,
    @SerialName(value = "description") val description: String? = null,
    @SerialName(value = "author") val author: String? = null,
    @SerialName(value = "ownerName") val ownerName: String? = null,
    @SerialName(value = "image") val image: String? = null,
    @SerialName(value = "artwork") val artwork: String? = null,
    @SerialName(value = "lastUpdateTime") val lastUpdateTime: Int? = null,
    @SerialName(value = "lastCrawlTime") val lastCrawlTime: Int? = null,
    @SerialName(value = "lastParseTime") val lastParseTime: Int? = null,
    @SerialName(value = "lastGoodHttpStatusTime") val lastGoodHttpStatusTime: Int? = null,
    @SerialName(value = "lastHttpStatus") val lastHttpStatus: Int? = null,
    @SerialName(value = "contentType") val contentType: String? = null,
    @SerialName(value = "itunesId") val itunesId: Int? = null,
    @SerialName(value = "generator") val generator: String? = null,
    @SerialName(value = "language") val language: String? = null,
    @SerialName(value = "explicit") val explicit: Boolean? = null,
    @SerialName(value = "type") val type: Type? = null,
    @SerialName(value = "medium") val medium: String? = null,
    @SerialName(value = "dead") val dead: Int? = null,
    @SerialName(value = "episodeCount") val episodeCount: Int? = null,
    @SerialName(value = "crawlErrors") val crawlErrors: Int? = null,
    @SerialName(value = "parseErrors") val parseErrors: Int? = null,
    @SerialName(value = "locked") val locked: Locked? = null,
    @SerialName(value = "imageUrlHash") val imageUrlHash: Int? = null,
    @SerialName(value = "newestItemPubdate") val newestItemPubdate: Int? = null
)

enum class Type(val code: Int) {
    RSS(0),
    Atom(1)
}

enum class Locked(val code: Int) {
    No(0),
    Yes(1)
}

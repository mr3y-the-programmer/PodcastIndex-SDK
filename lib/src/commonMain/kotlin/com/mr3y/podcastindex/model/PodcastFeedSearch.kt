package com.mr3y.podcastindex.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PodcastFeedSearch (
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "podcastGuid") val podcastGuid: String,
    @SerialName(value = "title") val title: String,
    @SerialName(value = "url") val url: String,
    @SerialName(value = "originalUrl") val originalUrl: String,
    @SerialName(value = "link") val link: String,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "author") val author: String,
    @SerialName(value = "ownerName") val ownerName: String,
    @SerialName(value = "image") val image: String,
    @SerialName(value = "artwork") val artwork: String,
    @SerialName(value = "lastUpdateTime") val lastUpdateTime: Instant,
    @SerialName(value = "lastCrawlTime") val lastCrawlTime: Instant,
    @SerialName(value = "lastParseTime") val lastParseTime: Instant,
    @SerialName(value = "lastGoodHttpStatusTime") val lastGoodHttpStatusTime: Instant,
    @SerialName(value = "lastHttpStatus") val lastHttpStatus: Int,
    @SerialName(value = "contentType") val contentType: String,
    @SerialName(value = "itunesId") val itunesId: Int? = null,
    @SerialName(value = "generator") val generator: String,
    @SerialName(value = "language") val language: String,
    @SerialName(value = "explicit") val explicit: Boolean,
    @SerialName(value = "type") val type: Type,
    @SerialName(value = "medium") val medium: String,
    @SerialName(value = "dead") val dead: Int,
    @SerialName(value = "episodeCount") val episodeCount: Int,
    @SerialName(value = "crawlErrors") val crawlErrors: Int,
    @SerialName(value = "parseErrors") val parseErrors: Int,
    @SerialName(value = "locked") val locked: Locked,
    @SerialName(value = "imageUrlHash") val imageUrlHash: Int,
    @SerialName(value = "newestItemPubdate") val newestItemPubdate: Instant? = null
)

enum class Type(val code: Int) {
    RSS(0),
    Atom(1)
}

enum class Locked(val code: Int) {
    No(0),
    Yes(1)
}

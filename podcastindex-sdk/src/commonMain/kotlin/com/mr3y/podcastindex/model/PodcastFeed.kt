package com.mr3y.podcastindex.model

import com.mr3y.podcastindex.extensions.CategoriesSerializer
import com.mr3y.podcastindex.extensions.InstantSerializer
import com.mr3y.podcastindex.extensions.LockedSerializer
import com.mr3y.podcastindex.extensions.TypeSerializer
import dev.drewhamilton.poko.Poko
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Poko
public class MultiplePodcastsResult(
    @SerialName(value = "feeds") public val feeds: List<PodcastFeed>,
    @SerialName(value = "count") public val count: Int,
    @SerialName(value = "description") public val description: String,
)

@Serializable
@Poko
public class SinglePodcastResult(
    @SerialName(value = "feed") public val feed: PodcastFeed,
    @SerialName(value = "description") public val description: String,
)

@Serializable
@Poko
public class PodcastFeed(
    @SerialName(value = "id") public val id: Long,
    @SerialName(value = "podcastGuid") public val podcastGuid: String,
    @SerialName(value = "title") public val title: String,
    @SerialName(value = "url") public val url: String,
    @SerialName(value = "originalUrl") public val originalUrl: String,
    @SerialName(value = "link") public val link: String,
    @SerialName(value = "description") public val description: String,
    @SerialName(value = "author") public val author: String,
    @SerialName(value = "ownerName") public val ownerName: String,
    @SerialName(value = "image") public val image: String,
    @SerialName(value = "artwork") public val artwork: String,
    @SerialName(value = "lastUpdateTime") @Serializable(InstantSerializer::class) public val lastUpdateTime: Instant,
    @SerialName(value = "lastCrawlTime") @Serializable(InstantSerializer::class) public val lastCrawlTime: Instant,
    @SerialName(value = "lastParseTime") @Serializable(InstantSerializer::class) public val lastParseTime: Instant,
    @SerialName(value = "lastGoodHttpStatusTime") @Serializable(InstantSerializer::class) public val lastGoodHttpStatusTime: Instant,
    @SerialName(value = "lastHttpStatus") public val lastHttpStatus: Int,
    @SerialName(value = "contentType") public val contentType: String,
    @SerialName(value = "itunesId") public val itunesId: Long? = null,
    @SerialName(value = "itunesType") public val itunesType: String? = null,
    @SerialName(value = "generator") public val generator: String?,
    @SerialName(value = "language") public val language: String,
    @SerialName(value = "explicit") public val explicit: Boolean? = null,
    @SerialName(value = "type") @Serializable(TypeSerializer::class) public val type: Type,
    @SerialName(value = "medium") public val medium: String? = null,
    @SerialName(value = "dead") public val dead: Int,
    @SerialName(value = "chash") public val chash: String? = null,
    @SerialName(value = "episodeCount") public val episodeCount: Int,
    @SerialName(value = "crawlErrors") public val crawlErrors: Int,
    @SerialName(value = "parseErrors") public val parseErrors: Int,
    @SerialName(value = "categories") @Serializable(CategoriesSerializer::class) public val categories: List<Category>?,
    @SerialName(value = "locked") @Serializable(LockedSerializer::class) public val locked: Locked,
    @SerialName(value = "imageUrlHash") public val imageUrlHash: Long? = null,
    @SerialName(value = "value") public val value: Value4Value? = null,
    @SerialName(value = "funding") public val funding: Funding? = null,
    @SerialName(value = "newestItemPubdate") @Serializable(InstantSerializer::class) public val newestItemPubdate: Instant? = null,
)

public enum class Type(public val code: Int) {
    RSS(0),
    Atom(1),
}

public enum class Locked(public val code: Int) {
    No(0),
    Yes(1),
}

@Serializable
@Poko
public class Value4Value(
    @SerialName(value = "model") public val model: Value4ValueModel,
    @SerialName(value = "destinations") public val destinations: List<Value4ValueDestination>,
)

@Serializable
@Poko
public class Value4ValueModel(
    @SerialName(value = "type") public val type: String,
    @SerialName(value = "method") public val method: String,
    @SerialName(value = "suggested") public val suggested: String? = null,
)

@Serializable
@Poko
public class Value4ValueDestination(
    @SerialName(value = "name") public val name: String,
    @SerialName(value = "address") public val address: String,
    @SerialName(value = "type") public val type: String,
    @SerialName(value = "split") public val split: Int,
    @SerialName(value = "fee") public val fee: Boolean? = null,
    @SerialName(value = "customKey") public val customKey: String? = null,
    @SerialName(value = "customValue") public val customValue: String? = null,
)

@Serializable
@Poko
public class Funding(
    @SerialName(value = "url") public val url: String,
    @SerialName(value = "message") public val message: String,
)

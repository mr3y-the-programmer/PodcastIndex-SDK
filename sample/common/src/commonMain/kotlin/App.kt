import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.sample.BuildConfig

suspend fun Sample() {
    val client = PodcastIndexClient {
        authentication {
            userAgent = "PodcastIndexSDK-Sample"
            authKey = BuildConfig.API_KEY
            authSecret = BuildConfig.API_SECRET
        }

        maxRetries = 4
    }

    val result = client.podcasts.byFeedId(741941)
    println(result.feed.title)
}

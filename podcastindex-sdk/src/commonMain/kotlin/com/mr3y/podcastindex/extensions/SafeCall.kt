package com.mr3y.podcastindex.extensions

import com.mr3y.podcastindex.model.BadRequestException
import com.mr3y.podcastindex.model.RateLimitExceededException
import com.mr3y.podcastindex.model.UnknownException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

internal suspend inline fun <reified T> withErrorHandling(block: () -> HttpResponse): T {
    val response = block()
    return when (response.status) {
        HttpStatusCode.OK -> response.body()
        HttpStatusCode.BadRequest -> {
            val description = response.body<JsonObject>()["description"]!!.jsonPrimitive.content
            throw BadRequestException(description)
        }
        HttpStatusCode.Unauthorized -> throw IllegalStateException("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
        HttpStatusCode.TooManyRequests -> throw RateLimitExceededException("API rate limit reached. Try again later or contact PodcastIndex org support if you think there is a mistake.")
        else -> throw UnknownException(response.bodyAsText())
    }
}

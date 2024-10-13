package com.mr3y.podcastindex.extensions

import com.mr3y.podcastindex.model.Value
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter

internal fun HttpRequestBuilder.parameterQuery(query: String) = parameter("q", query)

internal fun HttpRequestBuilder.parameterValue(value: Value) {
    if (value != Value.Any) {
        parameter("val", value.name.lowercase())
    }
}

internal fun HttpRequestBuilder.parameterLimit(limit: Int) {
    if (limit > 0) {
        parameter("max", limit)
    }
}

internal fun <T> HttpRequestBuilder.parameterList(
    label: String,
    values: List<T>,
    transform: ((T) -> CharSequence)? = null,
) {
    if (values.isNotEmpty()) {
        parameter(label, values.joinToString(separator = ",", transform = transform))
    }
}

internal fun HttpRequestBuilder.parameterBoolean(label: String, predicate: Boolean) {
    if (predicate) {
        parameter(label, true)
    }
}

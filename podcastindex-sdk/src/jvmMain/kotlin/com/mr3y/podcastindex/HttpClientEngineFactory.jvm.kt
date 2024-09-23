package com.mr3y.podcastindex

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun defaultHttpClientEngineFactory(): HttpClientEngineFactory<*> = OkHttp

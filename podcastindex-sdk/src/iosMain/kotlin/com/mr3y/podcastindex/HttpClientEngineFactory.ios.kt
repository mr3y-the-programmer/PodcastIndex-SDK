package com.mr3y.podcastindex

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

internal actual fun defaultHttpClientEngineFactory(): HttpClientEngineFactory<*> = Darwin
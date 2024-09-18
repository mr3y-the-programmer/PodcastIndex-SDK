package com.mr3y.podcastindex

import io.ktor.client.engine.HttpClientEngineFactory

internal expect fun defaultHttpClientEngineFactory(): HttpClientEngineFactory<*>

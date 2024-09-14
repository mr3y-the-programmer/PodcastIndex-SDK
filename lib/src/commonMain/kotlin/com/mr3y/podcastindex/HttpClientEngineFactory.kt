package com.mr3y.podcastindex

import io.ktor.client.engine.HttpClientEngineFactory

expect fun defaultHttpClientEngineFactory(): HttpClientEngineFactory<*>

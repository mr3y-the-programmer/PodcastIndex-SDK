package com.mr3y.podcastindex.model

import dev.drewhamilton.poko.Poko
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Poko
public class ValueFeedResult(
    @SerialName(value = "value") public val value: Value4Value,
    @SerialName(value = "description") public val description: String,
)

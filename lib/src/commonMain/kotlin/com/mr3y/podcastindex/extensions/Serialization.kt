package com.mr3y.podcastindex.extensions

import com.mr3y.podcastindex.model.EpisodeType
import com.mr3y.podcastindex.model.Explicit
import com.mr3y.podcastindex.model.Locked
import com.mr3y.podcastindex.model.Type
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class InstantSerializer : KSerializer<Instant> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Instant", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): Instant {
        val timestamp = decoder.decodeInt().toLong()
        return Instant.fromEpochMilliseconds(timestamp)
    }

    override fun serialize(encoder: Encoder, value: Instant) {
        // Serialization isn't implemented right now as support for endpoints
        // that allows writing/updating to the Index hasn't been added yet
    }
}

internal class ExplicitSerializer : KSerializer<Explicit> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Explicit", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): Explicit {
        return Explicit.entries.first { it.code == decoder.decodeInt() }
    }

    override fun serialize(encoder: Encoder, value: Explicit) {
        // Serialization isn't implemented right now as support for endpoints
        // that allows writing/updating to the Index hasn't been added yet
    }
}

internal class TypeSerializer : KSerializer<Type> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Type", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): Type {
        return Type.entries.first { it.code == decoder.decodeInt() }
    }

    override fun serialize(encoder: Encoder, value: Type) {
        // Serialization isn't implemented right now as support for endpoints
        // that allows writing/updating to the Index hasn't been added yet
    }
}

internal class LockedSerializer : KSerializer<Locked> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Locked", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): Locked {
        return Locked.entries.first { it.code == decoder.decodeInt() }
    }

    override fun serialize(encoder: Encoder, value: Locked) {
        // Serialization isn't implemented right now as support for endpoints
        // that allows writing/updating to the Index hasn't been added yet
    }
}

internal class EpisodeTypeSerializer : KSerializer<EpisodeType?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("EpisodeType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): EpisodeType? {
        return try {
            EpisodeType.valueOf(decoder.decodeString().capitalize())
        } catch (ex: IllegalArgumentException) {
            null
        }
    }

    override fun serialize(encoder: Encoder, value: EpisodeType?) {
        // Serialization isn't implemented right now as support for endpoints
        // that allows writing/updating to the Index hasn't been added yet
    }

    private fun String.capitalize(): String = replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

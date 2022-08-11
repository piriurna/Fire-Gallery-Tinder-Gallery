package com.artemissoftware.data.local.util.serializer

import androidx.datastore.core.Serializer
import com.artemissoftware.data.local.models.ProfileSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


@Suppress("BlockingMethodInNonBlockingContext")
object ProfileSerializer : Serializer<ProfileSettings> {

    override val defaultValue: ProfileSettings
        get() = ProfileSettings()


    override suspend fun readFrom(input: InputStream): ProfileSettings {
        return try {
            Json.decodeFromString(
                deserializer = ProfileSettings.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }


    override suspend fun writeTo(t: ProfileSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = ProfileSettings.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}
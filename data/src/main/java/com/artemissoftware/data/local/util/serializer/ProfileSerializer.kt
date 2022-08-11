package com.artemissoftware.data.local.util.serializer

import androidx.datastore.core.Serializer
import com.artemissoftware.data.local.models.ProfileSettings
import com.artemissoftware.data.mappers.toProfile
import com.artemissoftware.data.mappers.toProfileSettings
import com.artemissoftware.domain.models.profile.Profile
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


@Suppress("BlockingMethodInNonBlockingContext")
object ProfileSerializer : Serializer<Profile> {

    override val defaultValue: Profile
        get() = ProfileSettings().toProfile()


    override suspend fun readFrom(input: InputStream): Profile {
        return try {
            Json.decodeFromString(
                deserializer = ProfileSettings.serializer(),
                string = input.readBytes().decodeToString()
            ).toProfile()
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }


    override suspend fun writeTo(t: Profile, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = ProfileSettings.serializer(),
                value = t.toProfileSettings()
            ).encodeToByteArray()
        )
    }
}
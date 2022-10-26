package com.artemissoftware.data.local.util.serializer

import androidx.datastore.core.Serializer
import com.artemissoftware.data.local.models.AppSettings
import com.artemissoftware.data.mappers.toAppConfig
import com.artemissoftware.data.mappers.toAppSettings
import com.artemissoftware.domain.models.profile.AppConfig
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


@Suppress("BlockingMethodInNonBlockingContext")
object AppSettingsSerializer : Serializer<AppConfig> {

    override val defaultValue: AppConfig
        get() = AppSettings().toAppConfig()


    override suspend fun readFrom(input: InputStream): AppConfig {
        return try {
            Json.decodeFromString(
                deserializer = AppSettings.serializer(),
                string = input.readBytes().decodeToString()
            ).toAppConfig()
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }


    override suspend fun writeTo(t: AppConfig, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppSettings.serializer(),
                value = t.toAppSettings()
            ).encodeToByteArray()
        )
    }
}
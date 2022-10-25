package com.artemissoftware.data.local.util.serializer

import androidx.datastore.core.Serializer
import com.artemissoftware.data.local.models.FavoriteImages
import com.artemissoftware.data.local.models.ProfileSettings
import com.artemissoftware.data.mappers.toFavoriteImages
import com.artemissoftware.data.mappers.toProfile
import com.artemissoftware.data.mappers.toProfileSettings
import com.artemissoftware.data.mappers.toUserFavoriteImages
import com.artemissoftware.domain.models.UserFavoriteImages
import com.artemissoftware.domain.models.profile.Profile
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object FavoriteImagesSerializer : Serializer<UserFavoriteImages> {

    override val defaultValue: UserFavoriteImages
        get() = FavoriteImages().toUserFavoriteImages()


    override suspend fun readFrom(input: InputStream): UserFavoriteImages {
        return try {
            Json.decodeFromString(
                deserializer = FavoriteImages.serializer(),
                string = input.readBytes().decodeToString()
            ).toUserFavoriteImages()
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }


    override suspend fun writeTo(t: UserFavoriteImages, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = FavoriteImages.serializer(),
                value = t.toFavoriteImages()
            ).encodeToByteArray()
        )
    }
}
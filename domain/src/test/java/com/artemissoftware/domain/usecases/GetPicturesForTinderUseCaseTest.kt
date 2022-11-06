package com.artemissoftware.domain.usecases

import BaseUseCaseTest
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.domain.models.UserFavoriteImages
import com.artemissoftware.domain.models.profile.User
import com.artemissoftware.domain.repositories.AuthenticationRepository
import com.artemissoftware.domain.repositories.GalleryRepository
import com.artemissoftware.domain.repositories.ProfileDataStoreRepository
import com.artemissoftware.domain.usecases.tinder.GetPicturesForTinderUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetPicturesForTinderUseCaseTest : BaseUseCaseTest() {

    private lateinit var getPicturesForTinderUseCase: GetPicturesForTinderUseCase
    private lateinit var galleryRepository : GalleryRepository
    private lateinit var profileDataStoreRepository : ProfileDataStoreRepository
    private lateinit var authenticationRepository: AuthenticationRepository

    @Before
    fun setUp() {
        galleryRepository = mock()
        profileDataStoreRepository = mock()
        authenticationRepository = mock()
        getPicturesForTinderUseCase = GetPicturesForTinderUseCase(galleryRepository, profileDataStoreRepository, authenticationRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive data`() = runBlockingTest {
        val numberOfTinderImages = 3
        val mockUser = User(
            UserFavoriteImages.mockUserFavoriteImages.data.keys.first(),
            favorites = UserFavoriteImages.mockUserFavoriteImages.data.values.first(),
            name = null
        )
        whenever(authenticationRepository.getUser()).thenReturn(
            mockUser
        )

        val profilePreferencesFlow = flow { emit(UserFavoriteImages(UserFavoriteImages.mockUserFavoriteImages.data)) }


        whenever(profileDataStoreRepository.getProfile()).thenReturn(profilePreferencesFlow)

        whenever(galleryRepository.getPicturesForTinder(numberOfTinderImages, mockUser.favorites)).thenReturn(
            Picture.picturesMockList.filterNot { picture -> mockUser.favorites.contains(picture.id) }
        )


        val emissions = getPicturesForTinderUseCase().toList()

        val result = (emissions[0] as Resource)
        val giveawayGames = (result.data as? List<Picture>)
        assert(!giveawayGames.isNullOrEmpty())

        verify(galleryRepository, times(1)).getPicturesForTinder(numberOfTinderImages, mockUser.favorites)
    }
}
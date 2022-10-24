package com.artemissoftware.data.mappers

import com.artemissoftware.data.local.models.ProfileSettings
import com.artemissoftware.domain.models.profile.Profile
import com.artemissoftware.domain.models.profile.User
import com.google.firebase.auth.FirebaseUser

fun ProfileSettings.toProfile() : Profile{
    return Profile(notifications = this.notifications, favorites = this.favorites, firebaseToken = this.firebaseToken)
}

fun Profile.toProfileSettings() : ProfileSettings{
    return ProfileSettings(notifications = this.notifications, favorites = this.favorites, firebaseToken = this.firebaseToken)
}

fun FirebaseUser.toUser(): User{
    return User(name = displayName, email = email)
}
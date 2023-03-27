package com.example.taskhuman.dataModels

data class FavAddResponse(
    val favorite: Favorite,
    val message: String,
    val showFavoriteToast: Boolean,
    val success: Boolean
)
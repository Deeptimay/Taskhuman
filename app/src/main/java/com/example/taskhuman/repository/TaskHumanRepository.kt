package com.example.taskhuman.repository

import com.example.taskhuman.dataModels.*

interface TaskHumanRepository {
    suspend fun getListResults(query: String): TopicListResponse
    suspend fun addFavResults(favAddRequest: FavAddRequest): FavAddResponse
    suspend fun removeFavResults(favRemoveRequest: FavRemoveRequest): FavRemoveResponse
}
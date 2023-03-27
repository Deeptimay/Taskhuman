package com.example.taskhuman.repository

import androidx.lifecycle.LiveData
import com.example.taskhuman.dataModels.FavAddResponse
import com.example.taskhuman.dataModels.FavRemoveResponse
import com.example.taskhuman.dataModels.TopicListResponse

interface TaskHumanRepository {
    suspend fun getListResults(query: String): TopicListResponse
    suspend fun addFavResults(skillName: String, dictionaryName: String): FavAddResponse
    suspend fun removeFavResults(skillName: String): FavRemoveResponse
}
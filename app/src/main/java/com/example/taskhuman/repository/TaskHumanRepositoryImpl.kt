package com.example.taskhuman.repository

import com.example.taskhuman.base.api.TaskHumanApi
import com.example.taskhuman.dataModels.FavAddResponse
import com.example.taskhuman.dataModels.FavRemoveResponse
import com.example.taskhuman.dataModels.TopicListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskHumanRepositoryImpl @Inject constructor(private val taskHumanApi: TaskHumanApi) :
    TaskHumanRepository {
    override suspend fun getListResults(query: String): TopicListResponse {
        return taskHumanApi.getTopicList(query)
    }

    override suspend fun addFavResults(
        skillName: String,
        dictionaryName: String
    ): FavAddResponse {
        return taskHumanApi.addFavTopic(skillName, dictionaryName)
    }

    override suspend fun removeFavResults(skillName: String): FavRemoveResponse {
        return taskHumanApi.removeFavTopic(skillName)
    }
}
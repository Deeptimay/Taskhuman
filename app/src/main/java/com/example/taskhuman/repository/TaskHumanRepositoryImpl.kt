package com.example.taskhuman.repository

import com.example.taskhuman.base.api.TaskHumanApi
import com.example.taskhuman.dataModels.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskHumanRepositoryImpl @Inject constructor(private val taskHumanApi: TaskHumanApi) :
    TaskHumanRepository {
    override suspend fun getListResults(query: String): TopicListResponse {
        return taskHumanApi.getTopicList(query)
    }

    override suspend fun addFavResults(favAddRequest: FavAddRequest): FavAddResponse {
        return taskHumanApi.addFavTopic(favAddRequest)
    }

    override suspend fun removeFavResults(favRemoveRequest: FavRemoveRequest): FavRemoveResponse {
        return taskHumanApi.removeFavTopic(favRemoveRequest)
    }
}
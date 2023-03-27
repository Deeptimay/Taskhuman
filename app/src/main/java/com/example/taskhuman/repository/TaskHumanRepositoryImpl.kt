package com.example.taskhuman.repository

import androidx.lifecycle.LiveData
import com.example.taskhuman.base.api.TaskHumanApi
import com.example.taskhuman.dataModels.TopicListResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskHumanRepositoryImpl @Inject constructor(private val githubApi: TaskHumanApi) :
    TaskHumanRepository {

    override fun getListResults(query: String): LiveData<TopicListResponse> {
        TODO("Not yet implemented")
    }
}
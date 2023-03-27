package com.example.taskhuman.repository

import androidx.lifecycle.LiveData
import com.example.taskhuman.dataModels.TopicListResponse

interface TaskHumanRepository {
    fun getListResults(query: String): LiveData<TopicListResponse>
}
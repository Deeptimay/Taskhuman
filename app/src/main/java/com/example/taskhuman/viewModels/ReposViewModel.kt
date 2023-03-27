package com.example.taskhuman.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskhuman.dataModels.FavAddResponse
import com.example.taskhuman.dataModels.FavRemoveResponse
import com.example.taskhuman.dataModels.TopicListResponse
import com.example.taskhuman.repository.TaskHumanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val taskHumanRepository: TaskHumanRepository
) : ViewModel() {

    private val topicListResponseMutableLivedata: MutableLiveData<TopicListResponse> = MutableLiveData()
    private val favAddResponseMutableLivedata: MutableLiveData<FavAddResponse> = MutableLiveData()
    private val favRemoveResponseMutableLivedata: MutableLiveData<FavRemoveResponse> = MutableLiveData()

    suspend fun getListResults() {
        topicListResponseMutableLivedata.postValue(taskHumanRepository.getListResults(DEFAULT_QUERY))
    }

    suspend fun addFavResults(skillName: String, dictionaryName: String) {
        favAddResponseMutableLivedata.postValue(
            taskHumanRepository.addFavResults(
                skillName,
                dictionaryName
            )
        )
    }

    suspend fun removeFavResults(skillName: String) {
        favRemoveResponseMutableLivedata.postValue(taskHumanRepository.removeFavResults(skillName))
    }

    companion object {
        private const val DEFAULT_QUERY = "physical fitness"
    }
}
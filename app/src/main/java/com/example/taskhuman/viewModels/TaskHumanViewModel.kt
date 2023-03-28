package com.example.taskhuman.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskhuman.dataModels.*
import com.example.taskhuman.repository.TaskHumanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskHumanViewModel @Inject constructor(
    private val taskHumanRepository: TaskHumanRepository
) : ViewModel() {

    private val topicListResponseMutableLivedata: MutableLiveData<TopicListResponse> =
        MutableLiveData()
    private val favAddResponseMutableLivedata: MutableLiveData<FavAddResponse> = MutableLiveData()
    private val favRemoveResponseMutableLivedata: MutableLiveData<FavRemoveResponse> =
        MutableLiveData()

    fun getTopicListResponseMutableLivedata() = topicListResponseMutableLivedata
    fun getListResults() {
        viewModelScope.launch {
            try {
                val response = taskHumanRepository.getListResults(DEFAULT_QUERY)
                if (response.success)
                    topicListResponseMutableLivedata.postValue(response)
            } catch (ex: Exception) {
//                Toast.makeText("Server encountered error.")
            }
        }
    }

    fun addFavResults(skillName: String, dictionaryName: String) {
        viewModelScope.launch {
            try {
                val favAddRequest = FavAddRequest(skillName, dictionaryName)
                val response = taskHumanRepository.addFavResults(favAddRequest)
                if (response.success)
                    favAddResponseMutableLivedata.postValue(response)
            } catch (ex: Exception) {
//                Toast.makeText("Server encountered error.")
            }
        }
    }

    fun removeFavResults(skillName: String) {
        viewModelScope.launch {
            try {
                val favRemoveRequest = FavRemoveRequest(skillName)
                val response = taskHumanRepository.removeFavResults(favRemoveRequest)
                if (response.success)
                    favRemoveResponseMutableLivedata.postValue(response)
            } catch (ex: Exception) {
//                Toast.makeText("Server encountered error.")
            }
        }
    }

    companion object {
        private const val DEFAULT_QUERY = "physical fitness"
    }
}
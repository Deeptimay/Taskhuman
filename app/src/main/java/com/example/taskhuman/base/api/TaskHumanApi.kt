package com.example.taskhuman.base.api

import com.example.taskhuman.dataModels.TopicListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskHumanApi {

    @Headers(
        "Accept: */*",
        "Content-Type: application/json",
        "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQ3MTYsInVzZXJzIjp7InN0YXR1cyI6MCwidHlwZSI6MCwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZX0sImlhdCI6MTY3OTU3MzU4N30.gaiGbeN9tWIojmvSj0imKtCWW0wMhLzN-UjmXevzuyk"
    )
    @GET("v0.8/discover/topicDetails/physical%20fitness")
    suspend fun getTopicList(): TopicListResponse

    @Headers(
        "Accept: */*",
        "Content-Type: application/json",
        "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQ3MTYsInVzZXJzIjp7InN0YXR1cyI6MCwidHlwZSI6MCwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZX0sImlhdCI6MTY3OTU3MzU4N30.gaiGbeN9tWIojmvSj0imKtCWW0wMhLzN-UjmXevzuyk"
    )
    @POST("v0.8/favorite/add")
    suspend fun addFavTopic(
        @Query("skillName") skillName: String,
        @Query("dictionaryName") dictionaryName: String
    ): Any

    @Headers(
        "Accept: */*",
        "Content-Type: application/json",
        "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQ3MTYsInVzZXJzIjp7InN0YXR1cyI6MCwidHlwZSI6MCwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZX0sImlhdCI6MTY3OTU3MzU4N30.gaiGbeN9tWIojmvSj0imKtCWW0wMhLzN-UjmXevzuyk"
    )
    @POST("v0.8/favorite/remove")
    suspend fun removeFavTopic(
        @Query("skillName") skillName: String
    ): Any
}
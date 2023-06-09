package com.example.taskhuman.base.api

import com.example.taskhuman.dataModels.*
import retrofit2.http.*


interface TaskHumanApi {

    @Headers(
        "Accept: */*",
        "Content-Type: application/json",
        "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQ3MTYsInVzZXJzIjp7InN0YXR1cyI6MCwidHlwZSI6MCwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZX0sImlhdCI6MTY3OTU3MzU4N30.gaiGbeN9tWIojmvSj0imKtCWW0wMhLzN-UjmXevzuyk"
    )
    @GET("v0.8/discover/topicDetails/{topicName}")
    suspend fun getTopicList(
        @Path(
            value = "topicName",
            encoded = true
        ) topicName: String?
    ): TopicListResponse

    @Headers(
        "Accept: */*",
        "Content-Type: application/json",
        "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQ3MTYsInVzZXJzIjp7InN0YXR1cyI6MCwidHlwZSI6MCwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZX0sImlhdCI6MTY3OTU3MzU4N30.gaiGbeN9tWIojmvSj0imKtCWW0wMhLzN-UjmXevzuyk"
    )
    @POST("v0.8/favorite/add")
    suspend fun addFavTopic(@Body favAddRequest: FavAddRequest): FavAddResponse

    @Headers(
        "Accept: */*",
        "Content-Type: application/json",
        "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQ3MTYsInVzZXJzIjp7InN0YXR1cyI6MCwidHlwZSI6MCwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZX0sImlhdCI6MTY3OTU3MzU4N30.gaiGbeN9tWIojmvSj0imKtCWW0wMhLzN-UjmXevzuyk"
    )
    @POST("v0.8/favorite/remove")
    suspend fun removeFavTopic(@Body favRemoveRequest: FavRemoveRequest): FavRemoveResponse
}
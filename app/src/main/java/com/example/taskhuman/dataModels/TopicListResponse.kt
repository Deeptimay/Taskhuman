package com.example.taskhuman.dataModels

data class TopicListResponse(
    val isNextPage: Boolean,
    val message: Any,
    val skills: List<Skill>,
    val success: Boolean,
    val topicHeader: TopicHeader
)
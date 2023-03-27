package com.example.taskhuman.dataModels

data class BlogMetaData(
    val _id: String,
    val blogImageUrl: String,
    val estReadTime: Int,
    val link: String,
    val providerInfo: List<ProviderInfo>,
    val title: String
)
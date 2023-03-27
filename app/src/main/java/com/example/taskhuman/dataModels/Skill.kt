package com.example.taskhuman.dataModels

data class Skill(
    val availability: Availability,
    val blogMetaData: BlogMetaData,
    val columns: Int,
    val dictionaryName: String,
    val displayTileName: String,
    val isFavorite: Boolean,
    val moreProvidersAvailable: Boolean,
    val providerInfo: List<ProviderInfoX>,
    val skillIcon: String,
    val tileBackground: String,
    val tileColor: String,
    val tileName: String,
    val type: String
)
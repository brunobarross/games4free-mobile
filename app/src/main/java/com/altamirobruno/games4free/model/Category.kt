package com.altamirobruno.games4free.model

data class Category(
    val id: Int,
    val name: String,
    var giveaways: List<Giveaway>
)

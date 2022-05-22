package com.liven.demo.entity

data class Food(
    val name:String,
    val price: Float,
    val discount: Float = 0f,
    val imgSrc: String = "",
    val description: String = "Delicious Food!",
    val category: String
)

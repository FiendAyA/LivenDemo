package com.liven.demo.entity

data class Invoice(
    val dishes: MutableList<DishPerson> = mutableListOf(),
    var totalAmount: Float = 0f,
    var discount: Float = 0f,
    var tax: Float = 0f,
    var surcharges: Float = 0f,
    var payWay: String = ""
)

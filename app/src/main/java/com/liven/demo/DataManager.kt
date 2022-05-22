package com.liven.demo

import com.liven.demo.entity.Food

object DataManager {
    val foodList =
        mutableListOf(
            Food("Big Brekkie", 16f, category = "Food"),
            Food("Bruschetta", 8f, category = "Food"),
            Food("Poached Eggs", 12f, category = "Food"),
            Food("Coffee", 5f, category = "Drink"),
            Food("Tea", 3f, category = "Drink"),
            Food("Soda", 4f, category = "Drink"),
            Food("Garden Salad", 10f, category = "Food")
        )

}
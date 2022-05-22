package com.liven.demo.common

import android.util.Log
import com.liven.demo.base.BaseViewModel
import com.liven.demo.entity.DishPerson
import com.liven.demo.entity.Food
import com.liven.demo.entity.Invoice
import com.liven.demo.livedata.EventLiveData

class AppViewModel : BaseViewModel() {
    private val invoice = Invoice()
    val invoiceLiveData: EventLiveData<Invoice> = EventLiveData()

    fun changeFoodAmount(food: Food, amount: Int?) {
        Log.i("A", "$amount")
        invoiceLiveData.postValue(
            invoice.apply {
                val index = dishes.indexOfFirst {
                    it.food == food
                }
                if (amount == null || amount == 0) {
                    dishes.removeAt(index)
                } else {
                    if (index != -1) {
                        dishes[index].amount = amount
                    } else {
                        dishes.add(DishPerson(food, amount, 0))
                    }
                }
                var tempAmount = 0f
                dishes.forEach {
                    tempAmount += it.amount * it.food.price
                }
                totalAmount = tempAmount
            })
    }
}
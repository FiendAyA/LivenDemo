package com.liven.demo.common

import android.widget.Toast
import com.liven.demo.base.BaseViewModel
import com.liven.demo.base.appContext
import com.liven.demo.entity.DishPerson
import com.liven.demo.entity.Food
import com.liven.demo.entity.Invoice
import com.liven.demo.entity.Transaction
import com.liven.demo.livedata.EventLiveData

class AppViewModel : BaseViewModel() {
    var invoice = Invoice()
    val transactionLiveData: EventLiveData<Transaction> = EventLiveData()

    fun changeFoodAmount(food: Food, amount: Int, customerNo: Int) {
        invoice.apply {
            val index = dishes.indexOfFirst {
                it.food == food && it.personNumber == customerNo
            }
            if (index != -1) {
                if (amount == 0) {
                    dishes.removeAt(index)
                } else {
                    dishes[index].amount = amount
                }
            } else {
                dishes.add(DishPerson(food, amount, customerNo))
            }
            var tempAmount = 0f
            dishes.forEach {
                tempAmount += it.amount * it.food.price
            }
            totalAmount = tempAmount
        }
    }

    fun changeDiscount(discount: Float) {
        invoice.discount = discount
    }

    fun submitInvoice(finalPay: Float, paid: Int?) {
        if (paid == null) {
            Toast.makeText(appContext, "Don't miss-click!", Toast.LENGTH_SHORT).show()
            return
        }
        if (paid < finalPay) {
            Toast.makeText(appContext, "No enough money paid", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(appContext, "Succeed", Toast.LENGTH_SHORT).show()
            transactionLiveData.postValue(Transaction(paid.toFloat(), paid - finalPay, 0f, invoice))
            invoice = Invoice()
        }
    }
}
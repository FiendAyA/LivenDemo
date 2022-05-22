package com.liven.demo.common

import android.util.Log
import android.widget.Toast
import com.liven.demo.base.BaseViewModel
import com.liven.demo.base.appContext
import com.liven.demo.entity.DishPerson
import com.liven.demo.entity.Food
import com.liven.demo.entity.Invoice
import com.liven.demo.entity.Transaction
import com.liven.demo.livedata.EventLiveData

class AppViewModel : BaseViewModel() {
    private var invoice = Invoice()
    val invoiceLiveData: EventLiveData<Invoice> = EventLiveData()
    val transactionLiveData: EventLiveData<Transaction> = EventLiveData()

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

    fun submitInvoice(finalPay: Float, paid: Int?) {
        if (paid == null) {
            Toast.makeText(appContext, "Don't miss-click!", Toast.LENGTH_SHORT).show()
            return
        }
        if (paid < finalPay) {
            Toast.makeText(appContext, "No enough money paid", Toast.LENGTH_SHORT).show()
        } else {
            transactionLiveData.postValue(
                Transaction(
                    paid.toFloat(),
                    paid - finalPay,
                    finalPay,
                    invoice
                )
            )
            invoice = Invoice()
            invoiceLiveData.postValue(invoice)
        }
    }
}
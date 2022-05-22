package com.liven.demo.ui.invoice

import com.liven.demo.base.BaseViewModel
import com.liven.demo.entity.Invoice

class InvoiceViewModel : BaseViewModel() {
    companion object {
        const val PAY_WAY_CREDIT = "credit"
        const val PAY_WAY_CASH = "cash"
    }

    var finalPay = 0f

    fun generateFoodInfo(invoice: Invoice): String {
        invoice.apply {
            val s = StringBuilder()
            dishes.forEach {
                s.append("Name: ${it.food.name} price: ${it.food.price} amount: ${it.amount}\n")
            }
            calculateFinalPay(invoice)
            s.append("Total: $totalAmount \n")
                .append("Discount: $discount '\n")
                .append("Tax: $tax \n")
                .append("Surcharges: $surcharges\n")
                .append("PayWays: $payWay\n")
                .append("Finally: $finalPay")
            return s.toString()
        }
    }

    private fun calculateFinalPay(invoice: Invoice) {
        invoice.apply {
            val afterDiscount = if (discount < 1f) {
                totalAmount * (1 - discount)
            } else {
                totalAmount - discount
            }
            tax = afterDiscount * 0.1f
            surcharges = if (payWay == PAY_WAY_CREDIT) {
                afterDiscount * 0.012f
            } else {
                0f
            }
            finalPay = afterDiscount + tax + surcharges
        }
    }
}
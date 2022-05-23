package com.liven.demo.ui.invoice

import com.liven.demo.base.BaseViewModel
import com.liven.demo.entity.Invoice

class InvoiceViewModel : BaseViewModel() {
    companion object {
        const val PAY_WAY_CREDIT = "credit"
        const val PAY_WAY_CASH = "cash"
        const val PAY_TYPE_INDIVIDUAL = "Individual"
        const val PAY_TYPE_ONE = "One"
        const val PAY_TYPE_SPLIT = "Split"
    }

    val payTypeArray = arrayOf(PAY_TYPE_ONE, PAY_TYPE_INDIVIDUAL, PAY_TYPE_SPLIT)
    val payWayArray = arrayOf(PAY_WAY_CASH, PAY_WAY_CREDIT)
    var finalPay = 0f


    fun generateFoodInfo(invoice: Invoice, payType: String, payWay: String): String {
        val s = StringBuilder()
        if (payType == PAY_TYPE_INDIVIDUAL) {
            calculateSinglePay(s, invoice, payWay)
        } else {
            calculatePay(s, invoice, payWay)
        }

        return s.toString()
    }

    private fun calculateSinglePay(
        s: StringBuilder,
        invoice: Invoice,
        payWay: String
    ) {
        val map = invoice.dishes.groupBy { it.personNumber }
        finalPay = invoice.totalAmount
        map.forEach { m ->
            s.append("Customer ${m.key}\n")
            var sum = 0f
            m.value.forEach {
                s.append("${it.food.name} $${it.food.price} x${it.amount}\n")
                sum += it.food.price * it.amount
            }
            val tax = sum * 0.1f
            val surcharges = if (payWay == PAY_WAY_CREDIT) sum * 0.012f else 0f
            finalPay += tax + surcharges
            s.append("Sum: $sum\n")
                .append("Tax: $tax\n")
                .append("Surcharges: $surcharges\n")
                .append("Total: ${sum + tax + surcharges}\n\n")
        }
        val discountString = if (invoice.discount < 1f) {
            finalPay *= (1 - invoice.discount)
            "${invoice.discount * 100}%"
        } else {
            finalPay -= invoice.discount
            "${invoice.discount}"
        }
        s.append("Discount: $discountString\n")
            .append("Final: $finalPay")
    }

    private fun calculatePay(
        s: StringBuilder,
        invoice: Invoice,
        payWay: String
    ) {
        finalPay = invoice.totalAmount
        val tax = finalPay * 0.1f
        val surcharges = if (payWay == PAY_WAY_CREDIT) finalPay * 0.012f else 0f
        finalPay += tax + surcharges
        val discountString = if (invoice.discount < 1f) {
            finalPay *= (1 - invoice.discount)
            "${invoice.discount * 100}%"
        } else {
            finalPay -= invoice.discount
            "${invoice.discount}"
        }
        val map = invoice.dishes.groupBy { it.food }
        map.values.forEach {
            val amount = it.sumOf { list -> list.amount }
            s.append("${it[0].food.name} $${it[0].food.price} x${amount}\n")
        }
        s.append("Sum: $finalPay\n")
            .append("Tax: $tax\n")
            .append("Surcharges: $surcharges\n")
            .append("Discount: $discountString\n")
            .append("Final: $finalPay\n")
    }
}
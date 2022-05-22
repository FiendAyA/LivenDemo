package com.liven.demo.entity

data class Transaction(
    val paid: Float,
    val returned: Float,
    val remaining: Float,
    val invoice: Invoice,
    val payInfo: PayInfo? = null
)
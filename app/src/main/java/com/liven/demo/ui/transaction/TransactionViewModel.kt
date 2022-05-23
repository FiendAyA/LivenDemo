package com.liven.demo.ui.transaction

import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import com.liven.demo.base.BaseViewModel
import com.liven.demo.entity.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel : BaseViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("Transaction")

    fun uploadTransaction(transactions: MutableList<Transaction>) {
        viewModelScope.launch(Dispatchers.Default) {
            transactions.forEach {
                myRef.setValue(it)
            }
        }
    }
}
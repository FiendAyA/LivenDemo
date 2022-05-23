package com.liven.demo.ui.transaction

import android.util.Log
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.liven.demo.R
import com.liven.demo.base.QuickAdapter
import com.liven.demo.entity.Transaction

class TransactionAdapter(data: MutableList<Transaction>) :
    QuickAdapter<Transaction>(R.layout.item_transaction, data) {
    override fun convert(holder: BaseViewHolder, item: Transaction) {
        holder.setText(R.id.transaction_number, "Transaction #${getItemPosition(item)}")
            .setText(R.id.transaction_info, displayTransaction(item))
    }

    private fun displayTransaction(item: Transaction): String {
        val s = StringBuilder()
        s.append("Paid: ${item.paid}\n")
            .append("Returned: ${item.returned}\n")
            .append("Remaining: ${item.remaining}")
        return s.toString()
    }

    fun setData(transaction: Transaction) {
        data.add(transaction)
        Log.i("A", "$data")
        notifyItemInserted(data.size)
    }
}
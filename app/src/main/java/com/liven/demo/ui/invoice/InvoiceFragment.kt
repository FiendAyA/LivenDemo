package com.liven.demo.ui.invoice

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.liven.demo.common.CommonFragment
import com.liven.demo.databinding.FragmentInvoiceBinding

class InvoiceFragment : CommonFragment<InvoiceViewModel, FragmentInvoiceBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.payType.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mViewModel.payTypeArray
        )
        mViewBind.payWay.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mViewModel.payWayArray
        )
    }

    override fun createObserver() {
        mViewBind.apply {
            submitInvoice.setOnClickListener {
                appViewModel.submitInvoice(
                    mViewModel.finalPay,
                    paidEditText.text.toString().toIntOrNull()
                )
            }

            generateInvoice.setOnClickListener {
                val payTypeString = payType.selectedItem.toString()
                val payWayString = payWay.selectedItem.toString()
                val invoice =
                    mViewModel.generateFoodInfo(appViewModel.invoice, payTypeString, payWayString)
                invoiceInfo.text = invoice
            }

            discount.addTextChangedListener {
                val discountNo = it.toString().toFloatOrNull() ?: 0f
                appViewModel.changeDiscount(discountNo)
            }
        }
    }

    override fun lazyLoadData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }
}
package com.liven.demo.ui.invoice

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
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
                if (!invoiceInfo.text.isNullOrEmpty()) {
                    appViewModel.submitInvoice(
                        mViewModel.finalPay,
                        paidEditText.text.toString().toIntOrNull()
                    )
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please generate invoice first!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            generateInvoice.setOnClickListener {
                if (appViewModel.invoice.totalAmount > 0) {
                    val payTypeString = payType.selectedItem.toString()
                    val payWayString = payWay.selectedItem.toString()
                    val invoice =
                        mViewModel.generateFoodInfo(
                            appViewModel.invoice,
                            payTypeString,
                            payWayString
                        )
                    invoiceInfo.text = invoice
                } else {
                    Toast.makeText(
                        requireContext(),
                        "You must choose the food!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            discount.addTextChangedListener {
                val discountNo = it.toString().toFloatOrNull() ?: 0f
                appViewModel.changeDiscount(discountNo)
            }
        }

        appViewModel.transactionLiveData.observe(viewLifecycleOwner) {
            mViewBind.apply {
                paidEditText.text.clear()
                invoiceInfo.text = ""
                discount.text.clear()
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
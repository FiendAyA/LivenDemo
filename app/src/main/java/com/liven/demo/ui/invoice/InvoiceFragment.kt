package com.liven.demo.ui.invoice

import android.os.Bundle
import com.liven.demo.common.CommonFragment
import com.liven.demo.databinding.FragmentInvoiceBinding

class InvoiceFragment : CommonFragment<InvoiceViewModel, FragmentInvoiceBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {
        appViewModel.invoiceLiveData.observeSticky(viewLifecycleOwner) {
            val foodInfo = mViewModel.generateFoodInfo(it)
            mViewBind.foodInfo.text = foodInfo
        }

        mViewBind.submitInvoice.setOnClickListener {
            appViewModel.submitInvoice(
                mViewModel.finalPay,
                mViewBind.paidEditText.text.toString().toIntOrNull()
            )
        }
    }

    override fun lazyLoadData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }
}
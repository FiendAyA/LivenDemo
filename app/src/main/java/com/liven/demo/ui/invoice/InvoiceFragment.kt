package com.liven.demo.ui.invoice

import android.os.Bundle
import com.liven.demo.base.BaseVmVbFragment
import com.liven.demo.common.AppViewModel
import com.liven.demo.databinding.FragmentInvoiceBinding
import com.liven.demo.ext.getAppViewModel

class InvoiceFragment : BaseVmVbFragment<InvoiceViewModel, FragmentInvoiceBinding>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {
        getAppViewModel<AppViewModel>().invoiceLiveData.observeSticky(viewLifecycleOwner) {
            val foodInfo = mViewModel.generateFoodInfo(it)
            mViewBind.foodInfo.text = foodInfo
        }
    }

    override fun lazyLoadData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }
}
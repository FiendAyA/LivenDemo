package com.liven.demo.ui.transaction

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.liven.demo.common.CommonFragment
import com.liven.demo.databinding.FragmentTransactionBinding

class TransactionFragment : CommonFragment<TransactionViewModel, FragmentTransactionBinding>() {
    private val transactionAdapter = TransactionAdapter(mutableListOf())
    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.transactionRecyclerview.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun createObserver() {
        appViewModel.transactionLiveData.observeSticky(viewLifecycleOwner) {
            transactionAdapter.addData(it)
            mViewModel.uploadTransaction(it)
        }
    }

    override fun lazyLoadData() {
        shouldInterceptBackPressed = true
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

}
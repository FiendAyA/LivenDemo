package com.liven.demo.ui.home

import android.os.Bundle
import com.liven.demo.base.BaseVmVbFragment
import com.liven.demo.databinding.FragmentHomeBinding

class HomeFragment : BaseVmVbFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun createObserver() {
    }

    override fun lazyLoadData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

}
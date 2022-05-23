package com.liven.demo.ui.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.liven.demo.DataManager
import com.liven.demo.common.CommonFragment
import com.liven.demo.databinding.FragmentHomeBinding
import com.liven.demo.ext.nav

class HomeFragment : CommonFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.foodList.apply {
            adapter = FoodListAdapter(DataManager.foodList) { food, foodAmount, customerNo ->
                if (foodAmount > 0) {
                    appViewModel.changeFoodAmount(food, foodAmount, customerNo)
                }
            }
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun createObserver() {
    }

    override fun lazyLoadData() {
        shouldInterceptBackPressed = true
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

}
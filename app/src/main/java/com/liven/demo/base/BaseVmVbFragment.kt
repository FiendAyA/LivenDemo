package com.liven.demo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.liven.demo.ext.inflateBindingWithGeneric

abstract class BaseVmVbFragment<VM : BaseViewModel, VB : ViewBinding> : BaseFragment<VM>() {
    override fun layoutId() = 0

    private var _binding: VB? = null
    val mViewBind: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBindingWithGeneric(inflater, container, false)
        return mViewBind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
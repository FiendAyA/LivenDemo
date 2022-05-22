package com.liven.demo.common

import androidx.viewbinding.ViewBinding
import com.liven.demo.base.BaseViewModel
import com.liven.demo.base.BaseVmVbFragment
import com.liven.demo.ext.getAppViewModel

abstract class CommonFragment<VM : BaseViewModel, VB : ViewBinding> : BaseVmVbFragment<VM, VB>() {
    protected val appViewModel by lazy { getAppViewModel<AppViewModel>() }
}
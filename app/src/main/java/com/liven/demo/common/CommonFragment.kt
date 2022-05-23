package com.liven.demo.common

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.viewbinding.ViewBinding
import com.liven.demo.base.BaseViewModel
import com.liven.demo.base.BaseVmVbFragment
import com.liven.demo.ext.getAppViewModel
import com.liven.demo.ext.lastNavTime

abstract class CommonFragment<VM : BaseViewModel, VB : ViewBinding> : BaseVmVbFragment<VM, VB>() {
    protected val appViewModel by lazy { getAppViewModel<AppViewModel>() }
    protected var shouldInterceptBackPressed = false
    var lastClickTime = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (shouldInterceptBackPressed) {
                    quitApp()
                } else {
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })
    }

    private fun quitApp(interval: Long = 500) {
        val currentTime = System.currentTimeMillis()
        if (currentTime >= lastNavTime + interval) {
            Toast.makeText(requireContext(), "Click twice to quit the app", Toast.LENGTH_SHORT)
                .show()
            lastNavTime = currentTime
        } else {
            requireActivity().finishAndRemoveTask();
        }
    }
}
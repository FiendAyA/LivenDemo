package com.liven.demo.base

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.liven.demo.ext.getVmClazz
import com.liven.demo.network.NetState
import com.liven.demo.network.NetworkStateManager

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {
    private val handler = Handler()

    lateinit var mViewModel: VM

    private var isFirst: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        initView(savedInstanceState)
        createObserver()
        registerDefUIChange()
        initData()
    }

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            handler.postDelayed({
                lazyLoadData()
                NetworkStateManager.instance.mNetworkStateCallback.observe(this) {
                    if (!isFirst) {
                        onNetworkStateChanged(it)
                    }
                }
                isFirst = false
            }, lazyLoadTime())
        }
    }

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun createObserver()

    abstract fun lazyLoadData()

    abstract fun showLoading(message: String = "Requesting network...")

    abstract fun dismissLoading()

    private fun registerDefUIChange() {
        mViewModel.loadingChange.showDialog.observe(this) {
            showLoading(it)
        }
        mViewModel.loadingChange.dismissDialog.observe(this) {
            dismissLoading()
        }
    }

    open fun initData() {}

    open fun lazyLoadTime(): Long {
        return 300
    }

    open fun onNetworkStateChanged(netState: NetState) {}
}
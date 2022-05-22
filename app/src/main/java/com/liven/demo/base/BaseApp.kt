package com.liven.demo.base

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.liven.demo.common.AppViewModel

val appViewModel by lazy { ViewModelProvider(BaseApp()).get(AppViewModel::class.java) }

open class BaseApp : Application(), ViewModelStoreOwner {

    private lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()
    }

    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }
}
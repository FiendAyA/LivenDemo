package com.liven.demo.base

import androidx.lifecycle.ViewModel
import com.liven.demo.livedata.EventLiveData

open class BaseViewModel: ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    inner class UiLoadingChange {
        val showDialog by lazy { EventLiveData<String>() }
        val dismissDialog by lazy { EventLiveData<Boolean>() }
    }
}
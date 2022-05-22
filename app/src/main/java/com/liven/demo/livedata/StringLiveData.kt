package com.liven.demo.livedata

import androidx.lifecycle.MutableLiveData

class StringLiveData : MutableLiveData<String>() {

    override fun getValue(): String {
        return super.getValue() ?: ""
    }

}
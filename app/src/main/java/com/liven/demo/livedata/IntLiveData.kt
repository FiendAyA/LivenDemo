package com.liven.demo.livedata

import androidx.lifecycle.MutableLiveData

class IntLiveData : MutableLiveData<Int>() {

    override fun getValue(): Int {
        return super.getValue() ?: 0
    }
}
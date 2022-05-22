package com.liven.demo.base

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

abstract class QuickAdapter<T> constructor(layoutId: Int, data: MutableList<T>) :
    BaseQuickAdapter<T, BaseViewHolder>(layoutId, data) {
}
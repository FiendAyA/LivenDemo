package com.liven.demo.ui.home

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.liven.demo.R
import com.liven.demo.base.QuickAdapter
import com.liven.demo.entity.Food

class FoodListAdapter(data: MutableList<Food>, val method: (Food, Int?) -> Unit) :
    QuickAdapter<Food>(R.layout.item_food, data) {

    override fun convert(holder: BaseViewHolder, item: Food) {
        holder.setText(R.id.food_name, item.name).setText(R.id.food_description, item.description)
            .setText(R.id.food_price, "$${item.price}")
        Glide.with(holder.itemView).load(item.imgSrc).placeholder(R.drawable.img)
            .into(holder.getView(R.id.food_img))
        holder.getView<EditText>(R.id.food_amount).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

                Log.i("A", s.toString())
                method.invoke(item, s.toString().toIntOrNull())
            }
        })
    }
}
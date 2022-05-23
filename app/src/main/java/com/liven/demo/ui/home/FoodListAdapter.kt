package com.liven.demo.ui.home

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.liven.demo.R
import com.liven.demo.base.QuickAdapter
import com.liven.demo.entity.Food

class FoodListAdapter(data: MutableList<Food>, val method: (Food, Int, Int) -> Unit) :
    QuickAdapter<Food>(R.layout.item_food, data) {

    override fun convert(holder: BaseViewHolder, item: Food) {
        holder.setText(R.id.food_name, item.name).setText(R.id.food_description, item.description)
            .setText(R.id.food_price, "$${item.price}")
        Glide.with(holder.itemView).load(item.imgSrc).placeholder(R.drawable.img)
            .into(holder.getView(R.id.food_img))
        holder.getView<Button>(R.id.change_button).setOnClickListener {
            val foodAmount =
                holder.getView<EditText>(R.id.food_amount).text.toString().toIntOrNull() ?: 0
            if (foodAmount > 0) {
                val customerNo =
                    holder.getView<EditText>(R.id.customer_number).text.toString().toIntOrNull()
                        ?: 1
                method.invoke(item, foodAmount, customerNo)
            } else {
                Toast.makeText(context, "Please enter the food amount!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
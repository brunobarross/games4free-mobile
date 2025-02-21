package com.altamirobruno.games4free.view

import android.view.View
import android.widget.TextView
import com.altamirobruno.games4free.R
import com.altamirobruno.games4free.model.Category
import com.altamirobruno.games4free.model.Giveaway
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CategoryItem(val category: Category) : Item<CategoryItem.CategoryViewHolder>() {
    class CategoryViewHolder(view: View) : GroupieViewHolder(view)

    override fun createViewHolder(itemView: View) = CategoryViewHolder(itemView)

    override fun bind(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.title_category).text = category.name
    }

    override fun getLayout(): Int = R.layout.category_item

}
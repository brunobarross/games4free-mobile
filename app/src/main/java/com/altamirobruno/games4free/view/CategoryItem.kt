package com.altamirobruno.games4free.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.games4free.R
import com.altamirobruno.games4free.model.Category
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CategoryItem(val category: Category) : Item<CategoryItem.CategoryViewHolder>() {
    class CategoryViewHolder(view: View) : GroupieViewHolder(view)

    override fun createViewHolder(itemView: View) = CategoryViewHolder(itemView)

    override fun bind(viewHolder: CategoryViewHolder, position: Int) {
        val adapter = GroupieAdapter()
        val rv_giveaway = viewHolder.itemView.findViewById<RecyclerView>(R.id.rv_giveaway)
        val giveawayItems = category.giveaways.map { GiveawayItem(it) }
        viewHolder.itemView.findViewById<TextView>(R.id.title_category).text = category.name
        rv_giveaway.layoutManager =
            LinearLayoutManager(viewHolder.itemView.context, RecyclerView.HORIZONTAL, false)
        rv_giveaway.adapter = adapter
        adapter.addAll(giveawayItems)
        adapter.notifyDataSetChanged()


    }

    override fun getLayout(): Int = R.layout.category_item

}
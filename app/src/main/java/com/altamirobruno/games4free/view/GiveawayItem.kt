package com.altamirobruno.games4free.view

import android.view.View
import com.altamirobruno.games4free.model.Giveaway
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


class GiveawayItem(val giveaway: Giveaway) : Item<GiveawayItem.GiveawayViewHolder>() {
  class GiveawayViewHolder(view: View) : GroupieViewHolder(view)

  override fun createViewHolder(itemView: View) = GiveawayViewHolder(itemView)

  override fun bind(viewHolder: GiveawayViewHolder, position: Int) {
    TODO("Not yet implemented")
  }

  override fun getLayout(): Int {
    TODO("Not yet implemented")
  }


}

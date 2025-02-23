package com.altamirobruno.games4free.view

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import com.altamirobruno.games4free.R
import com.altamirobruno.games4free.model.Giveaway
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import java.net.URI


class GiveawayItem(val giveaway: Giveaway) : Item<GiveawayItem.GiveawayViewHolder>() {
    class GiveawayViewHolder(view: View) : GroupieViewHolder(view)

    override fun createViewHolder(itemView: View) = GiveawayViewHolder(itemView)

    fun openLink(view: View, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(intent)

    }

    override fun bind(viewHolder: GiveawayViewHolder, position: Int) {
        val giveawayCover = viewHolder.itemView.findViewById<ImageView>(R.id.giveaway_cover)
        val buttonGet = viewHolder.itemView.findViewById<Button>(R.id.btn_get)
        val giveawayPlatform = viewHolder.itemView.findViewById<TextView>(R.id.giveaway_platform)
        val giveawayStore = viewHolder.itemView.findViewById<TextView>(R.id.giveaway_store)
        viewHolder.itemView.findViewById<TextView>(R.id.giveaway_title).text = giveaway.title
        viewHolder.itemView.findViewById<TextView>(R.id.giveaway_description).text =
            giveaway.description
        viewHolder.itemView.findViewById<TextView>(R.id.giveaway_type).setText("${giveaway.type} |")
        giveawayPlatform.setText(giveaway.platforms.split(',')[0].toString())
        giveawayStore.setText(giveaway.platforms.split(',')[1].toString())

        Glide
            .with(viewHolder.itemView)
            .load(giveaway.thumbnail)
            .centerCrop()
            .placeholder(R.drawable.movie_cover_placeholder)
            .into(giveawayCover);



        buttonGet.setOnClickListener {
            openLink(viewHolder.itemView, giveaway.open_giveaway_url)

        }
    }

    override fun getLayout(): Int = R.layout.giveaway_item


}

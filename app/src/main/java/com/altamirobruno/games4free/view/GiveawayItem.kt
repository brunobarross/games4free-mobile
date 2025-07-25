package com.altamirobruno.games4free.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.altamirobruno.games4free.R
import com.altamirobruno.games4free.model.Giveaway
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import java.net.URI


class GiveawayItem(val giveaway: Giveaway) :
    Item<GiveawayItem.GiveawayViewHolder>() {
    class GiveawayViewHolder(view: View) : GroupieViewHolder(view)

    override fun createViewHolder(itemView: View) = GiveawayViewHolder(itemView)

    fun openLink(view: View, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(intent)

    }

    override fun bind(viewHolder: GiveawayViewHolder, position: Int) {
        val giveawayCover = viewHolder.itemView.findViewById<ImageView>(R.id.giveaway_cover)
        val giveawayPlatform = viewHolder.itemView.findViewById<TextView>(R.id.giveaway_platform)
        val giveawayStore = viewHolder.itemView.findViewById<TextView>(R.id.giveaway_store)
        val giveawayOldPrice = viewHolder.itemView.findViewById<TextView>(R.id.giveaway_price)
        val spannableString = SpannableString(giveaway.worth)
        val listPlatforms = giveaway.platforms.split(',')
        viewHolder.itemView.findViewById<TextView>(R.id.giveaway_title).text = giveaway.title
        viewHolder.itemView.findViewById<TextView>(R.id.giveaway_description).text =
            giveaway.description
        viewHolder.itemView.findViewById<TextView>(R.id.giveaway_type).setText("${giveaway.type} |")
        spannableString.setSpan(StrikethroughSpan(), 0, giveaway.worth.length, 0)
        giveawayOldPrice.setText(spannableString)


        if (listPlatforms.isNotEmpty()) {
            giveawayPlatform.setText(listPlatforms[0].toString())
            if (listPlatforms.size > 1) giveawayStore.setText(listPlatforms[1].toString())

        }




        Glide
            .with(viewHolder.itemView)
            .load(giveaway.thumbnail)
            .centerCrop()
            .fallback(R.drawable.movie_cover_placeholder)
            .placeholder(R.drawable.movie_cover_placeholder)
            .into(giveawayCover);

        viewHolder.itemView.setOnClickListener { it ->
            val bundle = Bundle()
            bundle.putInt("id", giveaway.id)
            it.findNavController().navigate(
                R.id.action_nav_home_to_nav_giveaway, bundle

            )

        }

    }

    override fun getLayout(): Int = R.layout.giveaway_item


}

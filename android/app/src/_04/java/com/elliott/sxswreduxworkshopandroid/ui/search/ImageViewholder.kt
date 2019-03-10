package com.elliott.sxswreduxworkshopandroid.ui.search

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic._04.viewholder_image.view.*

class ImageViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleTextView = itemView.title
    val descriptionTextView = itemView.description
    val imageView = itemView.image


    fun bind(title: String, description: String, imageUrl: String, position: Int, itemClickListener: ImageAdapter.ItemClickListener) {
        titleTextView.text = title
        descriptionTextView.text = description
        imageView.transitionName = imageUrl
        if (imageUrl.isNotEmpty()) {
            Picasso.get().load(imageUrl).into(imageView)
        }
        itemView.setOnClickListener {
            itemClickListener.onItemClicked(position, imageView)
        }
    }
}

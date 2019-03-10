package com.elliott.sxswreduxworkshopandroid.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elliott.sxswreduxworkshopandroid.R
import com.elliott.sxswreduxworkshopandroid.network.model.ImageCollectionItem

class ImageAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<ImageViewholder>() {

    interface ItemClickListener {

        fun onItemClicked(position: Int, view: View)

    }

    var items = emptyList<ImageCollectionItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ImageViewholder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.viewholder_image, p0, false)
        return ImageViewholder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ImageViewholder, p1: Int) {
        val item = items[p1]
        val data = item.data[0]
        val imageUrl = if (!item.links.isNullOrEmpty()) {
            item.links[0].href
        } else {
            ""
        }
        p0.bind(data.title, data.description ?: "", imageUrl, p1, itemClickListener)

    }

}
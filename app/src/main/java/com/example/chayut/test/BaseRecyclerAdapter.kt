package com.example.chayut.test

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class BaseRecyclerAdapter(private var stickerClickListener: StickerClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var recyclerList: List<BaseRecyclerViewItem>? = null
    private lateinit var view: View

    fun setRecyclerList(recyclerList: List<BaseRecyclerViewItem>) {
        this.recyclerList = recyclerList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            RecyclerViewType.TYPE_STICKER -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_sticker, parent, false)
                return StickerHolder(view)
            }
        }

        throw NullPointerException("View Type $viewType doesn't match with any existing order detail type")
    }

    override fun getItemCount() = recyclerList?.size ?: 0

    override fun getItemViewType(position: Int) = recyclerList?.get(position)?.type ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val recyclerViewItem = recyclerList?.get(position)

        if (holder is StickerHolder) {
            setUpSticker(holder, recyclerViewItem as StickerItem)
        }
    }

    private fun setUpSticker(stickerHolder: StickerHolder, stickerItem: StickerItem) {
        stickerHolder.tvName.text = stickerItem.name
        stickerHolder.tvUrl.text = stickerItem.full

        GlideApp.with(view)
                .load(stickerItem.thumb)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(stickerHolder.ivSticker)

        stickerHolder.itemView.setOnClickListener {
            stickerClickListener.onStickerClick(stickerItem)
        }

    }

    interface StickerClickListener {
        fun onStickerClick(stickerItem: StickerItem)
    }
}


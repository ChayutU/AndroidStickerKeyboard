package com.example.chayut.test

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class StickerHolder (itemView : View): RecyclerView.ViewHolder(itemView) {

    var tvName : TextView = itemView.findViewById(R.id.tv_name)
    var tvUrl : TextView = itemView.findViewById(R.id.tv_url)
    var ivSticker : ImageView = itemView.findViewById(R.id.iv_sticker)
}
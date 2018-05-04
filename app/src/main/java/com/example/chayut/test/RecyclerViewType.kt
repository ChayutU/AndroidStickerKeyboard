package com.example.chayut.test

import com.example.chayut.test.BaseRecyclerViewItem

class RecyclerViewType (type : Int) : BaseRecyclerViewItem(type){
    companion object {
        const val TYPE_STICKER = 1
    }
}
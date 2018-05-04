package com.example.chayut.test

class StickerItem(stickerItemDao: StickerDao.StickerItemDao) : BaseRecyclerViewItem(RecyclerViewType.TYPE_STICKER) {
    val name = stickerItemDao.name
    val full  = stickerItemDao.full
    val thumb = stickerItemDao.thumb

}
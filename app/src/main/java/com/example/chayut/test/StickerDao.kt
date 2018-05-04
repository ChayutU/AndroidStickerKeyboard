package com.example.chayut.test

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class StickerDao(

        @field:SerializedName("pack")
        val pack: List<PackItemDao?>? = null
) : Parcelable {
    fun objectFromData(json: String): StickerDao {
        return Gson().fromJson(json, StickerDao::class.java)
    }

    constructor(source: Parcel) : this(
            ArrayList<PackItemDao?>().apply { source.readList(this, PackItemDao::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(pack)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<StickerDao> = object : Parcelable.Creator<StickerDao> {
            override fun createFromParcel(source: Parcel): StickerDao = StickerDao(source)
            override fun newArray(size: Int): Array<StickerDao?> = arrayOfNulls(size)
        }
    }

    data class StickerItemDao(

            @field:SerializedName("thumb")
            val thumb: String? = null,

            @field:SerializedName("name")
            val name: String? = null,

            @field:SerializedName("full")
            val full: String? = null
    ) : Parcelable {
        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
            writeString(thumb)
            writeString(name)
            writeString(full)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<StickerItemDao> = object : Parcelable.Creator<StickerItemDao> {
                override fun createFromParcel(source: Parcel): StickerItemDao = StickerItemDao(source)
                override fun newArray(size: Int): Array<StickerItemDao?> = arrayOfNulls(size)
            }
        }
    }

    data class PackItemDao(

            @field:SerializedName("name")
            val name: String? = null,

            @field:SerializedName("icon")
            val icon: String? = null,

            @field:SerializedName("sticker")
            val sticker: List<StickerItemDao?>? = null,

            @field:SerializedName("version")
            val version: Int? = null
    ) : Parcelable {
        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.createTypedArrayList(StickerItemDao.CREATOR),
                source.readValue(Int::class.java.classLoader) as Int?
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
            writeString(name)
            writeString(icon)
            writeTypedList(sticker)
            writeValue(version)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<PackItemDao> = object : Parcelable.Creator<PackItemDao> {
                override fun createFromParcel(source: Parcel): PackItemDao = PackItemDao(source)
                override fun newArray(size: Int): Array<PackItemDao?> = arrayOfNulls(size)
            }
        }
    }
}


package com.nitzanwerber.picflow.dataModel.dto

import android.os.Parcel
import android.os.Parcelable

data class FlickerPrePhoto (
    val id: String? = null,
    val owner: String? = "",
    val secret: String? = "",
    val server: String? = "",
    val farm: String? = "",
    val title: String? = "",
    val ispublic: Int = 0,
    val isfriend: Int = 0,
    val isfamily: Int = 0,
    val url_s: String? = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(owner)
        parcel.writeString(secret)
        parcel.writeString(server)
        parcel.writeString(farm)
        parcel.writeString(title)
        parcel.writeInt(ispublic)
        parcel.writeInt(isfriend)
        parcel.writeInt(isfamily)
        parcel.writeString(url_s)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FlickerPrePhoto> {
        override fun createFromParcel(parcel: Parcel): FlickerPrePhoto {
            return FlickerPrePhoto(parcel)
        }

        override fun newArray(size: Int): Array<FlickerPrePhoto?> {
            return arrayOfNulls(size)
        }
    }
}

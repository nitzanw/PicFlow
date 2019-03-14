package com.nitzanwerber.picflow.dataModel.dto

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@Entity
data class FlickrPrePhoto (

    @SerializedName("id")
    @Expose
    val id: String = "",
    @SerializedName("owner")
    @Expose
    val owner: String = "",
    @SerializedName("secret")
    @Expose
    val secret: String = "",
    @SerializedName("server")
    @Expose
    val server: String = "",
    @SerializedName("farm")
    @Expose
    val farm: String = "",
    @SerializedName("title")
    @Expose
    val title: String = "",
    @SerializedName("ispublic")
    @Expose
    val ispublic: Int = 0,
    @SerializedName("isfriend")
    @Expose
    val isfriend: Int = 0,
    @SerializedName("isfamily")
    @Expose
    val isfamily: Int = 0,
    @SerializedName("url_l")
    @Expose
    var url_l: String = "",
    @PrimaryKey
    @NonNull
    var insertDate: Long = 0L

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
        parcel.readString(),
        parcel.readLong()
    )

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
        parcel.writeString(url_l)
        parcel.writeLong(insertDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FlickrPrePhoto> {
        override fun createFromParcel(parcel: Parcel): FlickrPrePhoto {
            return FlickrPrePhoto(parcel)
        }

        override fun newArray(size: Int): Array<FlickrPrePhoto?> {
            return arrayOfNulls(size)
        }
    }
}

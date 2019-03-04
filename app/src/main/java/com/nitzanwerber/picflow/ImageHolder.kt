package com.nitzanwerber.picflow

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import javax.inject.Inject


class ImageHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView: ImageView = itemView.findViewById<ImageView>(R.id.photo_image_view)


}
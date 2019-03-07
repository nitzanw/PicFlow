package com.nitzanwerber.picflow.views
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nitzanwerber.picflow.R


class ImageHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView: ImageView = itemView.findViewById(R.id.photo_image_view)


}
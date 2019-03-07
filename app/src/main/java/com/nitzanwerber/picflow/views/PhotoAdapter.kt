package com.nitzanwerber.picflow.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nitzanwerber.picflow.R
import com.nitzanwerber.picflow.dataModel.pojo.FlickerPrePhoto
import com.nitzanwerber.picflow.dataModel.pojo.FlickrPhotosSearchResponse
import com.squareup.picasso.Picasso
import java.util.ArrayList

class PhotoAdapter(
    private val myDataset: ArrayList<FlickerPrePhoto>
    , var picasso: Picasso) :
    RecyclerView.Adapter<ImageHolder>() {

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        picasso.load(myDataset[position].url_o).into(holder.imageView)
    }

    override fun getItemCount() = myDataset.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false) as ImageView

        return ImageHolder(imageView);
    }

    fun updateData(data: FlickrPhotosSearchResponse) {
        myDataset.add(data.photos.photo[0])
        this.notifyItemRangeInserted(myDataset.size - 1, 1);
    }


}
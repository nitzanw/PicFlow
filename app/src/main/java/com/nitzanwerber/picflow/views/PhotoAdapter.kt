package com.nitzanwerber.picflow.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nitzanwerber.picflow.R
import com.nitzanwerber.picflow.dataModel.dto.FlickrPrePhoto
import com.nitzanwerber.picflow.dataModel.dto.FlickrPhotosSearchResponse
import com.squareup.picasso.Picasso
import java.util.ArrayList


class PhotoAdapter(
    private var myDataset: ArrayList<FlickrPrePhoto>,
    var picasso: Picasso
) :
    RecyclerView.Adapter<PhotoAdapter.ImageHolder>() {

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        var photoUrl = myDataset[position].url_l
        if (photoUrl.isNullOrEmpty()) {
            //some photos don't have urls - thus we need to construct them
            photoUrl = createUrl(myDataset[position])
        }
        picasso.load(photoUrl)
            .placeholder( R.drawable.progress_animation)
            .into(holder.imageView)

    }

    private fun createUrl(flickrPrePhoto: FlickrPrePhoto): String {
        var farmId = flickrPrePhoto.farm
        var serverId = flickrPrePhoto.server
        var ID = flickrPrePhoto.id
        var secret = flickrPrePhoto.secret
        return "//http://farm$farmId.staticflickr.com/$serverId/$ID" + "_$secret.jpg"
    }

    override fun getItemCount() = myDataset.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false) as ImageView

        return ImageHolder(imageView);
    }

    fun updateData(data: FlickrPhotosSearchResponse) {
        myDataset.add(0,data.photos.photo[0])
        this.notifyDataSetChanged();
    }

    fun getDataSet(): ArrayList<FlickrPrePhoto> {
        return myDataset;
    }

    fun setDataset(data: List<FlickrPrePhoto>) {
        myDataset = ArrayList(data)
        this.notifyDataSetChanged();
    }

    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.photo_image_view)
    }
}
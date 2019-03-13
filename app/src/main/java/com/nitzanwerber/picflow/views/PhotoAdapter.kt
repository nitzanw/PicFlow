package com.nitzanwerber.picflow.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nitzanwerber.picflow.R
import com.nitzanwerber.picflow.dataModel.dto.FlickerPrePhoto
import com.nitzanwerber.picflow.dataModel.dto.FlickrPhotosSearchResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pic_flow_item.view.*
import java.util.ArrayList


class PhotoAdapter(
    private var myDataset: ArrayList<FlickerPrePhoto>,
    var picasso: Picasso
) :
    RecyclerView.Adapter<PhotoAdapter.ImageHolder>() {

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        Log.wtf("onBindViewHolder", myDataset[position].url_l)
        var photoUrl = myDataset[position].url_l
        if (photoUrl.isNullOrEmpty()) {
            //some photos don't have urls - thus we need to construct them
            photoUrl = createUrl(myDataset[position])
        }
        picasso.load(photoUrl)
            .placeholder( R.drawable.progress_animation)
            .into(holder.imageView)

    }

    private fun createUrl(flickerPrePhoto: FlickerPrePhoto): String? {
        var farmId = flickerPrePhoto.farm
        var serverId = flickerPrePhoto.server
        var ID = flickerPrePhoto.id
        var secret = flickerPrePhoto.secret
        return "//http://farm$farmId.staticflickr.com/$serverId/$ID" + "_$secret.jpg"
    }

    override fun getItemCount() = myDataset.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false) as ImageView

        return ImageHolder(imageView);
    }

    fun updateData(data: FlickrPhotosSearchResponse) {
        Log.wtf("updateData1", " update data! " + myDataset.size)
        myDataset.add(0,data.photos.photo[0])
        this.notifyDataSetChanged();
        Log.wtf("updateData2", " update data! " + myDataset.size)
    }

    fun getDataSet(): ArrayList<FlickerPrePhoto> {
        return myDataset;
    }

    fun setDataset(data : ArrayList<FlickerPrePhoto> ){
        myDataset = data;
    }
    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.photo_image_view)


    }

}
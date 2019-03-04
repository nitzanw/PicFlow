package com.nitzanwerber.picflow

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nitzanwerber.picflow.pojo.FlickerPhotos
import com.nitzanwerber.picflow.pojo.FlickerPrePhoto
import com.nitzanwerber.picflow.pojo.FlickrPhotosSearchResponse
import java.util.ArrayList

class PictureAdapter(private val myDataset: ArrayList<FlickerPrePhoto>) :
    RecyclerView.Adapter<PictureAdapter.PicViewHolder>() {

    class PicViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        holder.textView.text = myDataset[position].id
    }

    override fun getItemCount() = myDataset.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_item, parent, false) as TextView

        return PicViewHolder(textView);
    }

    fun updateData(data: FlickrPhotosSearchResponse) {
//        val delta = data.size - myDataset.size
//        if (delta > 0){
//            this.notifyItemRangeInserted(myDataset.size, delta);
//        }
    }


}
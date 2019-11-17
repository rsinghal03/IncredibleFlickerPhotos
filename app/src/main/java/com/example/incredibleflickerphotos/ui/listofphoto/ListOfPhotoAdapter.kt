package com.example.incredibleflickerphotos.ui.listofphoto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.incredibleflickerphotos.R
import com.example.incredibleflickerphotos.data.model.PhotoWithDiffSizeResponse

class ListOfPhotoAdapter(private val context: Context) : RecyclerView.Adapter<ListOfPhotoAdapter.ViewHolder>() {

    private val mListOfPhotos: ArrayList<PhotoWithDiffSizeResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mListOfPhotos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(mListOfPhotos[position].sizes.size[6].source)
            .centerCrop()
            .placeholder(R.drawable.place_holder)
            .into(holder.imageView)
    }

    fun updateList(listOfPhotos: ArrayList<PhotoWithDiffSizeResponse>) {
        mListOfPhotos.clear()
        mListOfPhotos.addAll(listOfPhotos)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: AppCompatImageView = view.findViewById(R.id.item_view)
    }
}
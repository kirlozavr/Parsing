package com.example.parsing.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.parsing.R
import com.example.parsing.entity.ImageEntity
import javax.inject.Inject

class ImagesAdapter @Inject constructor(private val context: Context): RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private var imagesList: MutableList<ImageEntity> = ArrayList()
    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView
        val textViewWidth: TextView
        val textViewHeight: TextView
        val progressBar: ProgressBar

        init {
            imageView = itemView.findViewById(R.id.imageView)
            textViewWidth = itemView.findViewById(R.id.textViewWidth)
            textViewHeight = itemView.findViewById(R.id.textViewHeight)
            progressBar = itemView.findViewById(R.id.progressBar)
        }
    }

    fun setList(imagesList: MutableList<ImageEntity>){
        this.imagesList = imagesList
        notifyDataSetChanged()
    }

    fun clearList(){
        imagesList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_image_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = imagesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageEntity = imagesList[position]
        holder.textViewWidth.text = "Ширина изображения: ${imageEntity.width}"
        holder.textViewHeight.text = "Высота изображения: ${imageEntity.height}"
        Glide
            .with(layoutInflater.context)
            .load(imageEntity.url)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }

            })
            .into(holder.imageView)
    }
}
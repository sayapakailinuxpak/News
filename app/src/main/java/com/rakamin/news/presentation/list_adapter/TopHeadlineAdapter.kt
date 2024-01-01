package com.rakamin.news.presentation.list_adapter

import android.annotation.SuppressLint
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rakamin.news.R
import com.rakamin.news.databinding.HeadlinesItemBinding
import com.rakamin.news.presentation.TopHeadlineUiState

private const val TAG = "TopHeadlineAdapter"

class TopHeadlineAdapter(
) :  RecyclerView.Adapter<TopHeadlineAdapter.ViewHolder>(){

    private lateinit var dataset: List<TopHeadlineUiState>
    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataset: List<TopHeadlineUiState>) {
        this.dataset = dataset
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlineAdapter.ViewHolder {
        val binding = HeadlinesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: TopHeadlineAdapter.ViewHolder, position: Int) {
        val item = this.dataset[position]
        holder.binding.apply {
            topHeadlineTitleText.movementMethod = ScrollingMovementMethod()
            topHeadlineImage.load(data = item.image) {
                crossfade(true)
                placeholder(drawableResId = R.drawable.default_thumbnail_image_landscape)
            }
            topHeadlineTitleText.text = item.title
            topHeadlineSourceText.text = item.sourceName
            Log.d(TAG, "onBindViewHolder TH: ${item.publishedAt}")
            topHeadlineDateText.text = item.publishedAt
        }

    }

    inner class ViewHolder(val binding: HeadlinesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
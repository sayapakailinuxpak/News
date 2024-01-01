package com.rakamin.news.presentation.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rakamin.news.R
import com.rakamin.news.databinding.NewsItemBinding
import com.rakamin.news.presentation.AllNewsUiState

class AllNewsAdapter(
    private val dataSet: List<AllNewsUiState>
) : RecyclerView.Adapter<AllNewsAdapter.ViewHolder>() {
    private lateinit var binding: NewsItemBinding

    inner class ViewHolder(binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        binding.apply {
            allNewsThumbnailImage.load(item.image) {
                placeholder(drawableResId = R.drawable.default_thumbnail_image_landscape)
            }
            allNewsSourceText.text = item.sourceName
            allNewsTitleText.text = item.title
            allNewsDateText.text = item.publishedAt
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
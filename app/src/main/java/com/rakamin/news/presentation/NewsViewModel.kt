package com.rakamin.news.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakamin.news.R
import com.rakamin.news.data.repository.NewsRepository
import com.rakamin.news.data.util.ResourceState
import kotlinx.coroutines.launch

private const val TAG = "NewsViewModel"
class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _uiState by lazy { MutableLiveData(NewsUiState()) }
    val uiState: LiveData<NewsUiState> = _uiState

    init {
        getTopHeadline()
        getAllNews()
    }

    private fun getTopHeadline() {
        viewModelScope.launch {
            newsRepository.getTopHeadline().collect {
                when(it) {
                    is ResourceState.Loading -> {
                        _uiState.value = _uiState.value?.copy(isLoading = it.isLoading)
                    }

                    is ResourceState.Success -> {

                        _uiState.value = _uiState.value?.copy(topHeadlineItems = it.data?.articles?.map { articleDto ->
                            TopHeadlineUiState(
                                image = articleDto.urlToImage ?: "",
                                title = articleDto.title ?: "",
                                sourceName = articleDto.source?.name ?: "",
                                publishedAt = articleDto.publishedAt?.slice(0..9) ?: ""
                            )
                        }!!)
                    }

                    is ResourceState.Error -> {
                        _uiState.value = _uiState.value?.copy(errorMessage = it.message)
                    }
                }
            }
        }
    }

    private fun getAllNews() {
        viewModelScope.launch {
            newsRepository.getNews().collect {
                when(it) {
                    is ResourceState.Loading -> {
                        Log.d(TAG, "getTopHeadline: viewmodel $it")
                        _uiState.value = _uiState.value?.copy(isLoading = it.isLoading)
                    }

                    is ResourceState.Success -> {
                        Log.d(TAG, "getTopHeadline: viewmodel $it")
                        _uiState.value = _uiState.value?.copy(allNewsItems = it.data?.articles?.map { articleDto ->
                            AllNewsUiState(
                                image = articleDto.urlToImage ?: "",
                                sourceName = articleDto.source?.name ?: "",
                                title = articleDto.title ?: "",
                                publishedAt = articleDto.publishedAt?.slice(0..9) ?: ""
                            )
                        }!!)
                    }

                    is ResourceState.Error -> {
                        Log.d(TAG, "getTopHeadline: viewmodel $it")
                        _uiState.value = _uiState.value?.copy(errorMessage = it.message)
                    }
                }
            }
            Log.d(TAG, "getTopHeadline: viewmodel uistate ${_uiState.value}")
        }
    }

}
package com.rakamin.news.data.repository

import android.util.Log
import com.rakamin.news.data.dto.NewsDto
import com.rakamin.news.data.service.NewsService
import com.rakamin.news.data.util.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

private const val TAG = "NewsRepository"

class NewsRepository(
    private val newsService: NewsService
) {
    suspend fun getTopHeadline(): Flow<ResourceState<NewsDto>> {
        return flow {
            emit(ResourceState.Loading(isLoading = true))
            try {
                val response = newsService.getTopHeadline()
                Log.d(TAG, "getTopHeadline: repoimpl $response")
                emit(ResourceState.Success(data = response))
            } catch (e: Exception) {
                Log.e(TAG, "getTopHeadline: repoimpl ${e.message.toString()}", )
                emit(ResourceState.Error(message = e.message.toString()))
            } finally {
                emit(ResourceState.Loading(isLoading = false))
            }
        }
    }

    suspend fun getNews(): Flow<ResourceState<NewsDto>> {
        return flow {
            emit(ResourceState.Loading(isLoading = true))
            try {
                val response = newsService.getNews()
                Log.d(TAG, "getTopHeadline: repoimpl $response")
                emit(ResourceState.Success(data = response))
            } catch (e: Exception) {
                Log.e(TAG, "getTopHeadline: repoimpl ${e.message.toString()}", )
                emit(ResourceState.Error(message = e.message.toString()))
            } finally {
                emit(ResourceState.Loading(isLoading = false))
            }
        }
    }
}
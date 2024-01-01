package com.rakamin.news.data.util

sealed class ResourceState<out T>() {
    data class Loading<T>(val isLoading: Boolean) : ResourceState<T>()
    data class Success<T>(val data: T?) : ResourceState<T>()
    data class Error<T>(val message: String) : ResourceState<T>()
}

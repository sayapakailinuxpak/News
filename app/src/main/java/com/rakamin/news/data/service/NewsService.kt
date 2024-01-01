package com.rakamin.news.data.service

import android.os.Build
import androidx.core.os.BuildCompat
import com.google.gson.internal.GsonBuildConfig
import com.rakamin.news.BuildConfig
import com.rakamin.news.data.dto.ArticleDto
import com.rakamin.news.data.dto.NewsDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface NewsService {

    @Headers("X-Api-Key: ${BuildConfig.API_KEY}")
    @GET("everything?q=indonesia")
    suspend fun getNews(): NewsDto

    @Headers("X-Api-Key: ${BuildConfig.API_KEY}")
    @GET("top-headlines?country=us&category=technology")
    suspend fun getTopHeadline(): NewsDto
}
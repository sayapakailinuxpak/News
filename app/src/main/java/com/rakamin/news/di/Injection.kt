package com.rakamin.news.di

import com.rakamin.news.data.repository.NewsRepository
import com.rakamin.news.data.service.ApiClient

object Injection {
    private val newsService = ApiClient.getNewsService()
    val provideRepository = NewsRepository(newsService = newsService)
}
package com.rakamin.news.presentation

import com.rakamin.news.data.dto.NewsDto

data class NewsUiState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val topHeadlineItems: List<TopHeadlineUiState> = listOf(TopHeadlineUiState()),
    val allNewsItems: List<AllNewsUiState> = listOf(AllNewsUiState())
)

data class TopHeadlineUiState(
    val image: String = "",
    val title: String = "",
    val sourceName: String = "",
    val publishedAt: String = ""
)

data class AllNewsUiState(
    val image: String = "",
    val sourceName: String = "",
    val title: String = "",
    val publishedAt: String = ""
)



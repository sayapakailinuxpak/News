package com.rakamin.news.data.dto

data class NewsDto(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<ArticleDto>? = null
)

data class ArticleDto(
    val source: SourceDto? = SourceDto(),
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

data class SourceDto(
    val id: String? = null,
    val name: String? = null
)

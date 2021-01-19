package com.matej.factorynews.model

data class NewsResponse (
    val status: String = "",
    val source: String = "",
    val sortBy: String = "",
    val articles: List<Articles>
)
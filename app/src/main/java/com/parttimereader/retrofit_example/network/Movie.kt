package com.parttimereader.retrofit_example.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



data class Movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

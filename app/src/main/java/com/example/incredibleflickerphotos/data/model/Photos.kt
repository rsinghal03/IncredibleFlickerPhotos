package com.example.incredibleflickerphotos.data.model

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: String
)
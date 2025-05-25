package com.yasir.ktor.model

data class Posts(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)
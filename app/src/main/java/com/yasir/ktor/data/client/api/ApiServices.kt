package com.yasir.ktor.data.client.api

import com.yasir.ktor.model.Posts

interface ApiServices {
    suspend fun getPosts(): List<Posts>
    suspend fun createPost(post: Posts): Posts
}

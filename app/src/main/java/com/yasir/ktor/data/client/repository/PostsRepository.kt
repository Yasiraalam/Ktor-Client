package com.yasir.ktor.data.client.repository

import com.yasir.ktor.domain.AuthRepository
import com.yasir.ktor.model.Posts
import kotlinx.coroutines.delay

interface PostsRepository {
    suspend fun fetchPosts(): List<Posts>
    suspend fun createPost(post: Posts): Posts
}

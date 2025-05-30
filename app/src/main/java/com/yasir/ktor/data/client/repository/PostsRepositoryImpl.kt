package com.yasir.ktor.data.client.repository

import com.yasir.ktor.data.client.api.ApiServices
import com.yasir.ktor.model.Posts

class PostsRepositoryImpl(private val apiServices: ApiServices) : PostsRepository {
    override suspend fun fetchPosts(): List<Posts> = apiServices.getPosts()
    override suspend fun createPost(post: Posts): Posts = apiServices.createPost(post)
}

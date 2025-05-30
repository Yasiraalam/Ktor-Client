package com.yasir.ktor.domain.use_case

import com.yasir.ktor.data.client.repository.PostsRepository
import com.yasir.ktor.domain.AuthRepository
import com.yasir.ktor.model.Posts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CreatePostUseCase(private val repository: PostsRepository) {
    operator fun invoke(post: Posts) = flow<Posts?> {
        emit(repository.createPost(post))
    }.catch {
        emit(null)
    }.flowOn(Dispatchers.IO)
}

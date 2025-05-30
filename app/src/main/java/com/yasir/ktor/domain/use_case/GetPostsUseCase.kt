package com.yasir.ktor.domain.use_case

import com.yasir.ktor.data.client.repository.PostsRepository
import com.yasir.ktor.domain.AuthRepository
import com.yasir.ktor.model.Posts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetPostsUseCase(private val postsRepository: PostsRepository) {

    operator fun invoke() = flow {
        emit(postsRepository.fetchPosts())
    }.catch {thowable->
        emit(thowable.localizedMessage as List<Posts>)
    }.flowOn(Dispatchers.IO)
}
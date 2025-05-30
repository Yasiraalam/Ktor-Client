package com.yasir.ktor.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasir.ktor.domain.use_case.CreatePostUseCase
import com.yasir.ktor.domain.use_case.GetPostsUseCase
import com.yasir.ktor.model.Posts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val createPostUseCase: CreatePostUseCase
) : ViewModel() {

    private val _posts = MutableStateFlow<List<Posts>>(emptyList())
    val posts = _posts.asStateFlow()

    fun fetchPosts() {
        getPostsUseCase()
            .onEach { _posts.value = it }
            .launchIn(viewModelScope)
    }

    fun addPost(post: Posts) {
        createPostUseCase(post)
            .onEach { newPost ->
                newPost?.let {
                    _posts.update { current -> current + it }
                }
            }
            .launchIn(viewModelScope)
    }


}

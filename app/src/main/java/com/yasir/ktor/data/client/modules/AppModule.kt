package com.yasir.ktor.data.client.modules

import com.yasir.ktor.data.client.KtorClient
import com.yasir.ktor.data.client.api.ApiServices
import com.yasir.ktor.data.client.repository.PostsRepository
import com.yasir.ktor.data.client.repository.PostsRepositoryImpl
import com.yasir.ktor.data.client.services.ApiServicesImpl
import com.yasir.ktor.domain.use_case.CreatePostUseCase
import com.yasir.ktor.domain.use_case.GetPostsUseCase
import com.yasir.ktor.view.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Provide Ktor HttpClient
    single { KtorClient.getClient() }

    // Provide ApiService
    single<ApiServices> { ApiServicesImpl(get()) }

    // Provide Repository
    single<PostsRepository> { PostsRepositoryImpl(get()) }

    // Provide UseCases
    factory { GetPostsUseCase(get()) }
    factory { CreatePostUseCase(get()) }

    // Provide ViewModel
    viewModel { PostsViewModel(get(), get()) }
}

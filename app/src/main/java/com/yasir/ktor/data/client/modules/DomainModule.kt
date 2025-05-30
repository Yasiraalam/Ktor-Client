package com.yasir.ktor.data.client.modules

import com.yasir.ktor.domain.use_case.GetPostsUseCase
import com.yasir.ktor.domain.use_case.CreatePostUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { CreatePostUseCase(get()) }
    factory { GetPostsUseCase (get()) }
}
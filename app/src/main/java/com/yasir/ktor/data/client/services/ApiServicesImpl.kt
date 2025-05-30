package com.yasir.ktor.data.client.services

import com.yasir.ktor.data.client.KtorClient.Companion.getClient
import com.yasir.ktor.data.client.api.ApiServices
import com.yasir.ktor.model.Posts
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path

class ApiServicesImpl(private val client: HttpClient): ApiServices {
    override suspend fun getPosts():List<Posts>{
        return getClient().get("/posts")
            .body<List<Posts>>()
    }

    override suspend fun createPost(post: Posts): Posts {
        return client.post {
            url {
                path("/posts")
            }
            contentType(ContentType.Application.Json)
            setBody(post)
        }.body()
    }

}
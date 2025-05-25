package com.yasir.ktor.client

import com.yasir.ktor.model.Posts
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {

    //https://jsonplaceholder.typicode.com/posts
    companion object {
        private fun getClient(): HttpClient = HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 3000
                requestTimeoutMillis = 3000
                connectTimeoutMillis = 3000
            }

            install(DefaultRequest) {
                url {
                    host = "jsonplaceholder.typicode.com"
                    protocol = URLProtocol.HTTPS
                    headers {
                        append(HttpHeaders.Authorization, "Bearer 1234567890")
                    }
                }
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
        }
        suspend fun getPosts():List<Posts>{
            return getClient().get("/posts")
                .body<List<Posts>>()
        }

        suspend fun postPost(post: Posts):Posts{
            return getClient().post{
                url {
                    path("/posts")
                }
                contentType(ContentType.Application.Json)
                setBody(post)
            }.body<Posts>()

        }

    }
}
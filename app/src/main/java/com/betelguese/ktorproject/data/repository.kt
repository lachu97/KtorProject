package com.betelguese.ktorproject.data

import com.betelguese.ktorproject.commons.Constants
import io.ktor.client.*
import io.ktor.client.request.*

class myrepo(
    val client: HttpClient
) : NetworkService {
    override suspend fun getcomments(): List<Comments> {
        return client.get(Constants.URL)
    }
}
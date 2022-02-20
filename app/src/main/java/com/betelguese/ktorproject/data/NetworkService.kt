package com.betelguese.ktorproject.data

interface NetworkService {
    suspend fun getcomments(): List<Comments>
}
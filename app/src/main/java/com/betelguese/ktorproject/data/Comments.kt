package com.betelguese.ktorproject.data

import com.betelguese.ktorproject.domain.comment
import kotlinx.serialization.*

@Serializable
data class Comments(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)

fun Comments.toCom(): comment {
    return comment(
        name = name,
        body = body,
        email = email
    )
}
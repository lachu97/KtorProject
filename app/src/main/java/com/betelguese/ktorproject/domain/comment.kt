package com.betelguese.ktorproject.domain

import kotlinx.serialization.*

@Serializable
data class comment(
    val name: String?,
    val body: String?,
    val email: String?,
)
data class commentstate(
    val isloading:Boolean = false,
    val comment : List<comment> = emptyList(),
    val error:String? = null
)
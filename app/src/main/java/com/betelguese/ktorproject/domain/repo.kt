package com.betelguese.ktorproject.domain

import com.betelguese.ktorproject.commons.Resource
import com.betelguese.ktorproject.data.myrepo
import com.betelguese.ktorproject.data.toCom
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class repos(private val myrepos: myrepo) {
    operator fun invoke(): Flow<Resource<List<comment>>> {
        return flow {
            try {
                emit(Resource.Loading<List<comment>>())
                delay(1500)
                val values = myrepos.getcomments().map {
                    it.toCom()
                }
                delay(1000)
                emit(Resource.Success<List<comment>>(values))
            } catch (e: IOException) {
                emit(Resource.Error<List<comment>>(e.localizedMessage))
            }
        }

    }
}
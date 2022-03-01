package com.betelguese.ktorproject.di

import com.betelguese.ktorproject.data.myrepo
import com.betelguese.ktorproject.domain.repos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object myproviders {

    @Provides
    fun sometandomstring(): String {
        return "Saravana"
    }

    @Provides
    @Singleton
    fun provideHttpclient(): HttpClient {
        return HttpClient(CIO) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
            install(Logging) {
                level = LogLevel.BODY
            }
            install(HttpTimeout){
                requestTimeoutMillis = 5000L
            }
        }
    }

    @Provides
    @Singleton
    fun providerepo(client: HttpClient): myrepo {
        return myrepo(client = client)
    }

    @Provides
    @Singleton
    fun providerepos(myrepo: myrepo): repos {
        return repos(myrepo)
    }
}
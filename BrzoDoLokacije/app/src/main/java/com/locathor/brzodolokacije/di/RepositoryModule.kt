package com.locathor.brzodolokacije.di

import com.locathor.brzodolokacije.data.repository.PostRepositoryImpl
import com.locathor.brzodolokacije.data.repository.UserRepositoryImpl
import com.locathor.brzodolokacije.domain.repository.PostRepository
import com.locathor.brzodolokacije.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract  fun bindsPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository
}
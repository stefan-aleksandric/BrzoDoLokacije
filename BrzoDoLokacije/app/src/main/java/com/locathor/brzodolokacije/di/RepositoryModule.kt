package com.locathor.brzodolokacije.di

import com.locathor.brzodolokacije.data.repository.BrzoDoLokacijeRepositoryImpl
import com.locathor.brzodolokacije.domain.repository.BrzoDoLokacijeRepository
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
    abstract fun bindBrzoDoLokacijeRepository(
        brzoDoLokacijeRepositoryImpl: BrzoDoLokacijeRepositoryImpl
    ): BrzoDoLokacijeRepository
}
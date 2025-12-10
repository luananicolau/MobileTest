package com.example.mobiletest.di

import com.example.mobiletest.repositories.TreeRepository
import com.example.mobiletest.repositories.TreeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TreeModule {

    @Binds
    @Singleton
    abstract fun bindTreeRepository(
        impl: TreeRepositoryImpl
    ): TreeRepository
}

package com.example.taskhuman.base.di

import com.example.taskhuman.repository.TaskHumanRepository
import com.example.taskhuman.repository.TaskHumanRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesGithubRepository(impl: TaskHumanRepositoryImpl): TaskHumanRepository
}
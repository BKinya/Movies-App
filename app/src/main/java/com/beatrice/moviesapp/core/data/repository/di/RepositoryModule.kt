package com.beatrice.moviesapp.core.data.repository.di

import com.beatrice.moviesapp.core.domain.repository.MovieRepository
import com.beatrice.moviesapp.core.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    abstract fun provideMoviesRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}
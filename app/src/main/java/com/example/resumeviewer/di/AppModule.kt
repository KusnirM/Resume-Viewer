package com.example.resumeviewer.di

import android.app.Application
import android.content.Context
import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.repo.ProjectsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application

    @Provides
    @Singleton
    @Repository
    fun provideProjectsRepository(@Local localDataSource: DataSource, @Remote remoteDataSource: DataSource): DataSource =
            ProjectsRepository(localDataSource = localDataSource, remoteDataSource = remoteDataSource)
}
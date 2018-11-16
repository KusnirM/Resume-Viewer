package com.example.resumeviewer.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.common.DATABASE_NAME
import com.example.resumeviewer.db.ProjectsDatabase
import com.example.resumeviewer.repo.LocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideProjectsDatabase(context: Context) =
        Room.databaseBuilder(context, ProjectsDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    @Local
    fun provideLocalDataSource(projectsDatabase: ProjectsDatabase): DataSource = LocalDataSource(projectsDatabase)
}
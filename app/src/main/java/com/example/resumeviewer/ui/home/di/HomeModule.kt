package com.example.resumeviewer.ui.home.di

import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.di.Repository
import com.example.resumeviewer.ui.home.mvp.HomeContract
import com.example.resumeviewer.ui.home.mvp.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @HomeScope
    fun provideHomePresenter(@Repository projectsRepository: DataSource, view: HomeContract.View): HomeContract.Presenter =
            HomePresenter(projectsRepository, view)
}
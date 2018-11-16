package com.example.resumeviewer.ui.home.di

import com.example.resumeviewer.ui.home.mvp.HomeActivity
import com.example.resumeviewer.ui.home.mvp.HomeContract
import dagger.Binds
import dagger.Module

@Module
abstract class HomeViewModule {
    @Binds
    @HomeScope
    abstract fun provideHomeView(homeActivity: HomeActivity): HomeContract.View
}
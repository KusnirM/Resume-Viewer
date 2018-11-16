package com.example.resumeviewer.di

import com.example.resumeviewer.ui.details.di.DetailsModule
import com.example.resumeviewer.ui.details.di.DetailsScope
import com.example.resumeviewer.ui.details.di.DetailsViewModule
import com.example.resumeviewer.ui.details.mvp.DetailsActivity
import com.example.resumeviewer.ui.home.di.HomeModule
import com.example.resumeviewer.ui.home.di.HomeScope
import com.example.resumeviewer.ui.home.di.HomeViewModule
import com.example.resumeviewer.ui.home.mvp.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [HomeModule::class, HomeViewModule::class])
    @HomeScope
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailsModule::class, DetailsViewModule::class])
    @DetailsScope
    abstract fun bindDetailsActivity(): DetailsActivity
}
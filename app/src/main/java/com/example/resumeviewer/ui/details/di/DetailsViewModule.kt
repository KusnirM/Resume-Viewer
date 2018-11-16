package com.example.resumeviewer.ui.details.di

import com.example.resumeviewer.ui.details.mvp.DetailsActivity
import com.example.resumeviewer.ui.details.mvp.DetailsContract
import dagger.Binds
import dagger.Module

@Module
abstract class DetailsViewModule {
    @Binds
    @DetailsScope
    abstract fun provideDetailsView(detailsActivity: DetailsActivity): DetailsContract.View
}
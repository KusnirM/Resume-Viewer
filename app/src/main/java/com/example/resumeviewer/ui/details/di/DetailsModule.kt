package com.example.resumeviewer.ui.details.di

import com.example.resumeviewer.ui.details.mvp.DetailsContract
import com.example.resumeviewer.ui.details.mvp.DetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {
    @Provides
    @DetailsScope
    fun provideDetailsPresenter(view: DetailsContract.View): DetailsContract.Presenter = DetailsPresenter(view)
}
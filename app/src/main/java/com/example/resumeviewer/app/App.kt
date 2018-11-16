package com.example.resumeviewer.app

import android.app.Activity
import android.app.Application
import com.example.resumeviewer.di.AppComponent
import com.example.resumeviewer.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector


}
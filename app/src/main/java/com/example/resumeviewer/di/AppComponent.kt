package com.example.resumeviewer.di

import android.app.Application
import com.example.resumeviewer.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidInjectionModule::class, BuildersModule::class, AppModule::class, NetworkModule::class,
    DatabaseModule::class])
@Singleton
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance fun application(application: Application): Builder
    }
}
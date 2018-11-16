package com.example.resumeviewer.di

import android.content.Context
import com.example.resumeviewer.base.DataSource
import com.example.resumeviewer.common.BASE_URL
import com.example.resumeviewer.common.CACHE_SIZE
import com.example.resumeviewer.common.TIMEOUT_SECONDS
import com.example.resumeviewer.net.ProjectsService
import com.example.resumeviewer.repo.RemoteDataSource
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideCache(context: Context) = Cache(context.cacheDir, CACHE_SIZE)

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideProjectsService(retrofit: Retrofit): ProjectsService = retrofit.create(ProjectsService::class.java)

    @Provides
    @Singleton
    @Remote
    fun provideRemoteDataSource(projectsService: ProjectsService): DataSource = RemoteDataSource(projectsService)
}
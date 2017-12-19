package com.commissionsinc.pokemodern.di

import android.app.Application
import com.commissionsinc.pokemodern.model.api.BaseUrl
import com.commissionsinc.pokemodern.model.api.ResourceService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module class RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application) : Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val original = it.request()
            val request = original.newBuilder().build()
            val response = it.proceed(request)
            response
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return builder.cache(cache)
                .addInterceptor(interceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient, baseUrl: BaseUrl): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl.baseUrl)
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun provideResourceService(retrofit: Retrofit): ResourceService = retrofit.create(ResourceService::class.java)
}
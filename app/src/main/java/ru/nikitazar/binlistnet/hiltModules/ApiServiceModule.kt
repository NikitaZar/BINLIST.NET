package ru.nikitazar.binlistnet.hiltModules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikitazar.binlistnet.api.ApiService
import ru.nikitazar.binlistnet.api.okhttp
import ru.nikitazar.binlistnet.api.retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService = retrofit(okhttp()).create(ApiService::class.java)
}
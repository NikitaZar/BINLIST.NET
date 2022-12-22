package ru.nikitazar.binlistnet.api

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nikitazar.binlistnet.BuildConfig
import ru.nikitazar.binlistnet.dto.CardData

fun okhttp(): OkHttpClient = OkHttpClient.Builder().build()

fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BuildConfig.BASE_URL)
    .client(client)
    .build()

interface ApiService {
    @GET("{cardNumber}")
    suspend fun get(@Path("cardNumber") cardNumber: Int): Response<CardData>
}
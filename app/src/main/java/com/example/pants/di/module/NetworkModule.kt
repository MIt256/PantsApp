package com.example.pants.di.module

import com.example.pants.data.network.api.ColorApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://www.thecolorapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ColorApiService::class.java) }
}
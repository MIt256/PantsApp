package com.example.pants.di.module

import com.example.pants.data.repository.ColorRepository
import com.example.pants.data.repository.ColorRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val productRepositoryModule = module {
    singleOf(::ColorRepositoryImpl) { bind<ColorRepository>() }
}

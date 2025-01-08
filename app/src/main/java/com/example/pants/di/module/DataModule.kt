package com.example.pants.di.module

import org.koin.dsl.module

val dataModule = module {
    includes(networkModule, productRepositoryModule, useCaseModule)
}

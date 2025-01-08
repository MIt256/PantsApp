package com.example.pants.di.module

import com.example.pants.presentation.SharedGameViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::SharedGameViewModel)
}

package by.kesso.pixabaytest.domain.di

import by.kesso.pixabaytest.domain.usecase.GetImagesUseCase
import org.koin.dsl.module

val usecase = module {
    single { GetImagesUseCase(get()) }
}
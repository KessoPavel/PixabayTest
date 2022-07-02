package by.kesso.pixabaytest.domain.di

import by.kesso.pixabaytest.domain.usecase.GetImagesUseCase
import by.kesso.pixabaytest.domain.usecase.LoginUseCase
import by.kesso.pixabaytest.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val usecase = module {
    single { GetImagesUseCase(get()) }
    single { LoginUseCase() }
    single { RegisterUseCase() }
}
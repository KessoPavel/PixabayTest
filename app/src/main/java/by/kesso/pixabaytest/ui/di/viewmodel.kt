package by.kesso.pixabaytest.ui.di

import by.kesso.pixabaytest.ui.home.HomeViewModel
import by.kesso.pixabaytest.ui.imageDetail.ImageDetailViewModel
import by.kesso.pixabaytest.ui.login.LoginViewModel
import by.kesso.pixabaytest.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodel = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ImageDetailViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}
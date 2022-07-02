package by.kesso.pixabaytest.repository

import by.kesso.pixabaytest.domain.repository.PixeImageRepository
import by.kesso.pixabaytest.repository.remote.RemoteRepository
import org.koin.dsl.module

val repository = module {
    single { RemoteRepository() as PixeImageRepository }
}
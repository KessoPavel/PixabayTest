package by.kesso.pixabaytest.repository.di

import by.kesso.pixabaytest.domain.repository.LoginRepository
import by.kesso.pixabaytest.domain.repository.PixeImageRepository
import by.kesso.pixabaytest.repository.login.RoomLoginRepository
import by.kesso.pixabaytest.repository.login.db.AppDataBase
import by.kesso.pixabaytest.repository.remote.RemoteRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repository = module {
    single { RemoteRepository() } bind PixeImageRepository::class
    single { RoomLoginRepository(get()) } bind LoginRepository::class

    single { AppDataBase.getInstance(get()) }
    single { get<AppDataBase>().getUserDao() }
}
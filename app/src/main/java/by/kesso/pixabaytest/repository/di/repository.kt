package by.kesso.pixabaytest.repository

import by.kesso.pixabaytest.domain.repository.LoginRepository
import by.kesso.pixabaytest.domain.repository.PixeImageRepository
import by.kesso.pixabaytest.repository.login.RoomLoginRepository
import by.kesso.pixabaytest.repository.login.db.AppDataBase
import by.kesso.pixabaytest.repository.remote.RemoteRepository
import org.koin.dsl.module
import kotlin.math.sin

val repository = module {
    single { RemoteRepository() as PixeImageRepository }
    single { RoomLoginRepository(get()) as LoginRepository }

    single { AppDataBase.getInstance(get()) }
    single { get<AppDataBase>().getUserDao() }
}
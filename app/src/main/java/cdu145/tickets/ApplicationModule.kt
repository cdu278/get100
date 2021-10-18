package cdu145.tickets

import androidx.room.Room
import org.koin.dsl.module

val ApplicationModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "database").build() }
}
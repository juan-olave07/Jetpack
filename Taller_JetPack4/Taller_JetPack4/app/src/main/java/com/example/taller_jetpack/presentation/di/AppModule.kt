package com.example.taller_jetpack.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.taller_jetpack.data.local.AppDatabase
import com.example.taller_jetpack.data.local.TransaccionDao
import com.example.taller_jetpack.data.repository.TransaccionRepository
import com.example.taller_jetpack.data.repository.TransaccionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "finanzas_db")
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    fun provideDao(db: AppDatabase): TransaccionDao = db.transaccionDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        impl: TransaccionRepositoryImpl
    ): TransaccionRepository
}
package com.example.taller_jetpack.di

// Importaciones necesarias para Room, Hilt y contexto de aplicación
import android.content.Context
import androidx.room.Room
import com.example.taller_jetpack.data.api.AppDatabase
import com.example.taller_jetpack.data.api.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

// Indica que esta clase es un módulo de Hilt
@Module

// Define que las dependencias vivirán mientras viva la aplicación
@InstallIn(SingletonComponent::class)

// Objeto que provee dependencias relacionadas con la base de datos
object DatabaseModule {

    // Indica que esta función provee una instancia
    @Provides

    // Singleton garantiza una sola instancia de la base de datos
    @Singleton
    fun provideDatabase(

        // Obtiene el contexto global de la aplicación
        @ApplicationContext context: Context

    ): AppDatabase =

        // Construye la base de datos Room
        Room.databaseBuilder(

            // Contexto de la aplicación
            context,

            // Clase de la base de datos
            AppDatabase::class.java,

            // Nombre de la base de datos
            "app_db"

        )

            // Crea la instancia de la base de datos
            .build()

    // Provee el DAO de ciudades
    @Provides
    fun provideCityDao(db: AppDatabase): CityDao =

        // Retorna el DAO desde la base de datos
        db.cityDao()
}
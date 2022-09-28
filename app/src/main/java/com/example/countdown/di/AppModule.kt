package com.example.countdown.di

import android.content.Context
import androidx.room.Room
import com.example.countdown.model.CountdownDatabase
import com.example.countdown.model.CountdownDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesCountdownsDao(countdownDatabase: CountdownDatabase): CountdownDatabaseDao =
        countdownDatabase.countdownDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): CountdownDatabase =
        Room.databaseBuilder(context,
            CountdownDatabase::class.java, "countdown_db")
            .fallbackToDestructiveMigration()
            .build()
}
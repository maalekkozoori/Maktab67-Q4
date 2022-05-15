package com.example.maktab_q4.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.maktab_q4.data.local.db.RoomUserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) : RoomUserDataBase =
        Room.databaseBuilder(context , RoomUserDataBase::class.java,"user_database").fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUserDao(db:RoomUserDataBase) = db.userDao()
}
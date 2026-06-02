package com.tabrita.di

import android.content.Context
import androidx.room.Room
import com.tabrita.data.local.BookmarkDao
import com.tabrita.data.local.TaBritaDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): TaBritaDatabase {
        return Room.databaseBuilder(
            context,
            TaBritaDatabase::class.java,
            "tabrita_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBookmarkDao(database: TaBritaDatabase): BookmarkDao {
        return database.bookmarkDao()
    }
}

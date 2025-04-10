package com.example.notes.android.presentation.di

import android.content.Context
import com.example.notes.data.NotesDb
import com.example.notes.data.getDatabaseBuilder
import com.example.notes.data.getRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideNotesDb(
        @ApplicationContext context: Context
    ): NotesDb {
        val builder = getDatabaseBuilder(context)
        return getRoomDatabase(builder)
    }
}
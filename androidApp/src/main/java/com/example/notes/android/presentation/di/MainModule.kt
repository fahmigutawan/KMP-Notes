package com.example.notes.android.presentation.di

import android.content.Context
import com.example.notes.android.presentation.data.NotesRepository
import com.example.notes.data.NotesDb
import com.example.notes.data.NotesDbWrapper
import com.example.notes.data.NotesSource
import com.example.notes.data.getDatabaseBuilder
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
        return NotesDbWrapper().getRoomDatabase(builder)
    }

    @Provides
    @Singleton
    fun provideNotesRepository(
        db: NotesDb
    ): NotesRepository {
        val source = NotesSource(db)
        return NotesRepository(source)
    }
}
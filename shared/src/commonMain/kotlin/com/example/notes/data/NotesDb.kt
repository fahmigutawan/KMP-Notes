@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.example.notes.data

import NoteEntity
import NotesDao
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [NoteEntity::class],
    version = 1
)
@ConstructedBy(NotesDbFactory::class)
abstract class NotesDb : RoomDatabase() {
    abstract val notesDao: NotesDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object NotesDbFactory:RoomDatabaseConstructor<NotesDb>{
    override fun initialize(): NotesDb
}

class NotesDbWrapper{
    fun getRoomDatabase(
        builder: RoomDatabase.Builder<NotesDb>
    ): NotesDb {
        return builder
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}




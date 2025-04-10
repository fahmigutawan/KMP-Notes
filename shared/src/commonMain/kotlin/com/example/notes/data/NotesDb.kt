package com.example.notes.data

import NoteEntity
import NotesDao
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
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

fun getRoomDatabase(
    builder: RoomDatabase.Builder<NotesDb>
): NotesDb {
    return builder
        .fallbackToDestructiveMigration(true)
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}




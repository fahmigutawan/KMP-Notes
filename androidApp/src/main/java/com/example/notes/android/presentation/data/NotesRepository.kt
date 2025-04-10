package com.example.notes.android.presentation.data

import NoteEntity
import android.content.Context
import com.example.notes.data.NotesDb
import com.example.notes.data.getDatabaseBuilder
import com.example.notes.data.getRoomDatabase
import javax.inject.Inject

class NotesRepository @Inject constructor(
    db: NotesDb
){
    private val dao = db.notesDao

    suspend fun getAllNotes() = dao.getAll()

    suspend fun insertNote(
        title: String,
        description: String,
        createdAt: Long,
        updatedAt: Long?
    ) = dao.insert(
        NoteEntity(
            title = title,
            description = description,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    )

    suspend fun updateNote(
        id: Int,
        title: String,
        description: String,
        updatedAt: Long
    ) = dao.update(
        id = id,
        title = title,
        description = description,
        updatedAt = updatedAt
    )

    suspend fun deleteNote(id: Int) = dao.delete(id)
}
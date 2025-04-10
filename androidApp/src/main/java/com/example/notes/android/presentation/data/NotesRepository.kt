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

    suspend fun insertNotes(
        id: Int = 0,
        title: String,
        description: String,
        createdAt: String,
        updatedAt: String
    ) = dao.insert(
        NoteEntity(
            id = id,
            title = title,
            description = description,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    )

    suspend fun deleteNote(id: Int) = dao.delete(id)
}
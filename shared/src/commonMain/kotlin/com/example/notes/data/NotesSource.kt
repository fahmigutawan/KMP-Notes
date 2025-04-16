package com.example.notes.data

import NoteEntity
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

class NotesSource (
    db: NotesDb
){
    private val dao = db.notesDao

    @NativeCoroutines
    fun getAllNotes() = dao.getAll()

    @NativeCoroutines
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

    @NativeCoroutines
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

    @NativeCoroutines
    suspend fun deleteNote(id: Int) = dao.delete(id)
}
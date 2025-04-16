package com.example.notes.android.presentation.data

import com.example.notes.data.NotesSource
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val source: NotesSource,
) {
    fun getAllNotes() = source.getAllNotes()

    suspend fun insertNote(
        title: String,
        description: String,
        createdAt: Long,
        updatedAt: Long?,
    ) = source.insertNote(
        title = title,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    suspend fun updateNote(
        id: Int,
        title: String,
        description: String,
        updatedAt: Long,
        ) = source.updateNote(
        id = id,
        title = title,
        description = description,
        updatedAt = updatedAt
    )

    suspend fun deleteNote(id: Int) = source.deleteNote(id)
}
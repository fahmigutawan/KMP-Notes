package com.example.notes.android

import NoteEntity
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.android.presentation.data.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
) : ViewModel() {
    private val _notes = MutableStateFlow(emptyList<NoteEntity>())
    val notes get() = _notes.asStateFlow()

    val selectedNoteToEdit = mutableStateOf<NoteEntity?>(null)
    val selectedNoteToDelete = mutableStateOf<NoteEntity?>(null)
    val tmpAddedNote = mutableStateOf<NoteEntity?>(null)

    fun addNote(
        title: String,
        description: String,
    ) {
        viewModelScope.launch {
            notesRepository.insertNote(
                title = title,
                description = description,
                createdAt = Date().time,
                updatedAt = null
            )
        }
    }

    fun startAddLogic(){
        tmpAddedNote.value = NoteEntity(
            title = "",
            description = "",
            createdAt = 0L,
            updatedAt = null
        )
    }

    fun updateNote(
        id: Int,
        title: String,
        description: String,
    ) {
        viewModelScope.launch {
            notesRepository.updateNote(
                id = id,
                title = title,
                description = description,
                updatedAt = Date().time
            )
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            notesRepository.deleteNote(id)
        }
    }

    init {
        viewModelScope.launch {
            notesRepository.getAllNotes().collect {
                _notes.value = it
            }
        }
    }
}
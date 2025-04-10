package com.example.notes.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.android.presentation.data.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val notesRepository: NotesRepository
):ViewModel() {
    fun addNotes(){
        viewModelScope.launch {
            notesRepository.insertNotes(
                id = 0,
                title = "Ini adalah title",
                description = "Ini adalah description",
                createdAt = "",
                updatedAt = ""
            )
        }
    }

    fun deleteNotes(){
        viewModelScope.launch {
            notesRepository.deleteNote(0)
        }
    }

    fun getAllNotes() {
        viewModelScope.launch {
            val s = notesRepository.getAllNotes()
            Log.e("DARI NOTES", "List: $s")
        }
    }
}
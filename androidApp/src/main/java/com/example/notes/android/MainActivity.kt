package com.example.notes.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.Greeting
import com.example.notes.android.presentation.component.DeleteWarningDialog
import com.example.notes.android.presentation.component.EditableNotesItem
import com.example.notes.android.presentation.component.EmptyContent
import com.example.notes.android.presentation.component.NotesItem
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<MainViewModel>()
            val notes = viewModel.notes.collectAsState()

            if (viewModel.selectedNoteToDelete.value != null) {
                DeleteWarningDialog(
                    onCancel = {
                        viewModel.selectedNoteToDelete.value = null
                    },
                    onDeleteClick = {
                        viewModel.selectedNoteToDelete.value?.let { note ->
                            viewModel.deleteNote(note.id)
                            viewModel.selectedNoteToDelete.value = null
                        }
                    }
                )
            }

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (notes.value.isNotEmpty() || viewModel.tmpAddedNote.value != null) {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    item {
                                        Spacer(Modifier)
                                    }

                                    items(notes.value) { note ->
                                        if (viewModel.selectedNoteToEdit.value?.id == note.id) {
                                            EditableNotesItem(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp),
                                                onSaveClick = {
                                                    viewModel.updateNote(
                                                        id = note.id,
                                                        title = viewModel.selectedNoteToEdit.value?.title
                                                            ?: "",
                                                        description = viewModel.selectedNoteToEdit.value?.description
                                                            ?: ""
                                                    )
                                                    viewModel.selectedNoteToEdit.value = null
                                                },
                                                onCancelClick = {
                                                    viewModel.selectedNoteToEdit.value = null
                                                },
                                                title = viewModel.selectedNoteToEdit.value?.title
                                                    ?: "",
                                                onTitleChange = {
                                                    viewModel.selectedNoteToEdit.value =
                                                        viewModel.selectedNoteToEdit.value?.copy(
                                                            title = it
                                                        )
                                                },
                                                description = viewModel.selectedNoteToEdit.value?.description
                                                    ?: "",
                                                onDescriptionChange = {
                                                    viewModel.selectedNoteToEdit.value =
                                                        viewModel.selectedNoteToEdit.value?.copy(
                                                            description = it
                                                        )
                                                }
                                            )
                                        } else {
                                            NotesItem(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp),
                                                title = note.title,
                                                description = note.description,
                                                createdAt = note.createdAt,
                                                updatedAt = note.updatedAt,
                                                onEditClick = {
                                                    viewModel.selectedNoteToEdit.value = note
                                                },
                                                onDeleteClick = {
                                                    viewModel.selectedNoteToDelete.value = note
                                                }
                                            )
                                        }
                                    }

                                    if (viewModel.tmpAddedNote.value != null) {
                                        item {
                                            EditableNotesItem(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp),
                                                onSaveClick = {
                                                    viewModel.addNote(
                                                        title = viewModel.tmpAddedNote.value?.title
                                                            ?: "",
                                                        description = viewModel.tmpAddedNote.value?.description
                                                            ?: ""
                                                    )

                                                    viewModel.tmpAddedNote.value = null
                                                },
                                                onCancelClick = {
                                                    viewModel.tmpAddedNote.value = null
                                                },
                                                title = viewModel.tmpAddedNote.value?.title ?: "",
                                                onTitleChange = {
                                                    viewModel.tmpAddedNote.value =
                                                        viewModel.tmpAddedNote.value?.copy(
                                                            title = it
                                                        )
                                                },
                                                description = viewModel.tmpAddedNote.value?.description
                                                    ?: "",
                                                onDescriptionChange = {
                                                    viewModel.tmpAddedNote.value =
                                                        viewModel.tmpAddedNote.value?.copy(
                                                            description = it
                                                        )
                                                }
                                            )
                                        }
                                    } else {
                                        if (notes.value.isNotEmpty()) {
                                            item {
                                                Button(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(horizontal = 16.dp),
                                                    onClick = {
                                                        viewModel.startAddLogic()
                                                    }
                                                ) {
                                                    Text("Tambah Note")
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                EmptyContent(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 48.dp),
                                    onAddClick = {
                                        viewModel.startAddLogic()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

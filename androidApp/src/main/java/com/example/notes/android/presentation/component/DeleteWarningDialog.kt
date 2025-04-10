package com.example.notes.android.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DeleteWarningDialog(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onDeleteClick: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onCancel,
        title = {
            Text(
                text = "Hapus Note",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Text(
                text = "Apakah kamu yakin akan menghapus note ini?",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            Button (
                onClick = onDeleteClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancel
            ) {
                Text("Cancel")
            }
        },
        shape = MaterialTheme.shapes.medium
    )
}
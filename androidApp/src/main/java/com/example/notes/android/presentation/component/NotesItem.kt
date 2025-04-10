package com.example.notes.android.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onClick: () -> Unit,
    date: String,
) {
    val expand = remember { mutableStateOf(false) }
    ElevatedCard(
        modifier = modifier,
        onClick = {
            expand.value = !expand.value
            onClick()
        },
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )
    ) {
        AnimatedContent(expand.value) { _expand ->
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = description,
                    maxLines = if (_expand) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Clip
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Edit,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNotesItem(modifier: Modifier = Modifier) {
    Box(Modifier.background(Color.White)) {
        NotesItem(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            title = "Ini adalah title",
            description = "Ini adalah description",
            date = "xxxxxx",
            onClick = {
                //TODO
            }
        )
    }
}
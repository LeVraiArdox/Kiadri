package com.lvardox.kiadri.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.HideImage
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import com.lvardox.kiadri.models.Task
import com.lvardox.kiadri.ui.components.ExpandedMemoryDialog
import com.lvardox.kiadri.ui.components.ScrapbookCard

@Composable
fun HistoryScreen(completedTasks: List<Task>) {
    var expandedTask by remember { mutableStateOf<Task?>(null) }

    val context = LocalContext.current

    val db = Firebase.firestore
    val storage = Firebase.storage

    if (completedTasks.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column{
                Icon(
                    Icons.Rounded.HideImage,
                    contentDescription = "Encore aucun souvenir. Au boulot !",
                    modifier = Modifier.align(Alignment.CenterHorizontally).width(100.dp).height(100.dp),
                    tint = MaterialTheme.colorScheme.outline
                )
                Text(
                    text = "Encore aucun souvenir. Au boulot !",
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(completedTasks) { task ->
                ScrapbookCard(
                    task = task,
                    onClick = { expandedTask = task }
                )
            }
        }
    }

    expandedTask?.let { task ->
        ExpandedMemoryDialog(
            task = task,
            onDismiss = { expandedTask = null },
            onDelete = { taskToDelete ->
                taskToDelete.photoUri?.let { uri ->
                    try {
                        storage.getReferenceFromUrl(uri).delete()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Erreur de serveur !!", Toast.LENGTH_SHORT).show()
                        println("Erreur firebase: ${e.message}")
                    }
                }

                db.collection("tasks").document(taskToDelete.id).delete()
                expandedTask = null
            }
        )
    }
}
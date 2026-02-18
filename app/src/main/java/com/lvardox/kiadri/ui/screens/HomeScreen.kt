package com.lvardox.kiadri.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage

import com.lvardox.kiadri.models.Task
import com.lvardox.kiadri.ui.components.TaskItem

import java.io.File

@Composable
fun HomeScreen(tasks: MutableList<Task>) {
    val context = LocalContext.current
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var currentTask by remember { mutableStateOf<Task?>(null) }
    val tasks = remember { mutableStateListOf<Task>() }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) showConfirmDialog = true
        }
    )

    fun launchCamera(task: Task) {
        val file = File(context.externalCacheDir, "photo_${task.id}.jpg")
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
        photoUri = uri
        currentTask = task
        cameraLauncher.launch(uri)
    }

    if (tasks.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column{
                Icon(
                    Icons.Rounded.Checklist,
                    contentDescription = "Aucune tâche à faire",
                    modifier = Modifier.align(Alignment.CenterHorizontally).width(100.dp).height(100.dp),
                    tint = MaterialTheme.colorScheme.outline
                )
                Text(
                    text = "Aucune tâche à faire !",
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks.filter { !it.isCompleted }) { task ->
                TaskItem(task, onCameraClick = { launchCamera(task) })
            }
        }

        if (showConfirmDialog && photoUri != null) {
            AlertDialog(
                onDismissRequest = { showConfirmDialog = false },
                title = { Text("Valider l'activité ?") },
                text = {
                    Column {
                        AsyncImage(
                            model = photoUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(200.dp),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("C'est bon, on valide l'activité ?")
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        val index = tasks.indexOf(currentTask)
                        if (index != -1) {
                            tasks[index] = tasks[index].copy(
                                isCompleted = true,
                                photoUri = photoUri.toString()
                            )
                        }
                        showConfirmDialog = false
                    }) { Text("C'est okay !") }
                },
                dismissButton = {
                    TextButton(onClick = { showConfirmDialog = false }) { Text("Bof, on la refait !") }
                }
            )
        }
    }
}
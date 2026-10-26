package com.lvardox.kiadri.ui.screens

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import com.lvardox.kiadri.models.Task
import com.lvardox.kiadri.ui.components.SegmentedListGroup
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File

enum class TaskSortOption(val displayName: String) {
    NEWEST("Plus récents"),
    OLDEST("Plus anciens"),
    TITLE_ASC("Titre (A - Z)"),
    TITLE_DESC("Titre (Z - A)")
}

@Composable
fun HomeScreen(tasks: MutableList<Task>) {
    val context = LocalContext.current
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var currentTask by remember { mutableStateOf<Task?>(null) }
    var taskForPhotoChoice by remember { mutableStateOf<Task?>(null) }

    var searchQuery by remember { mutableStateOf("") }
    var sortOption by remember { mutableStateOf(TaskSortOption.NEWEST) }
    var showSortMenu by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val db = Firebase.firestore
    val storage = Firebase.storage

    var isUploading by remember { mutableStateOf(false) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) showConfirmDialog = true
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                photoUri = uri
                showConfirmDialog = true
            }
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

    val uncompletedTasks = tasks.filter { !it.completed }

    val filteredAndSortedTasks = remember(uncompletedTasks, searchQuery, sortOption) {
        uncompletedTasks
            .filter { task ->
                searchQuery.isBlank() || task.title.contains(searchQuery, ignoreCase = true)
            }
            .let { list ->
                when (sortOption) {
                    TaskSortOption.NEWEST -> list.sortedBy { it.createdAt ?: 0L }
                    TaskSortOption.OLDEST -> list.sortedByDescending { it.createdAt ?: 0L }
                    TaskSortOption.TITLE_ASC -> list.sortedBy { it.title.lowercase() }
                    TaskSortOption.TITLE_DESC -> list.sortedByDescending { it.title.lowercase() }
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (uncompletedTasks.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Rechercher une tâche...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Rechercher"
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Effacer la recherche"
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = CircleShape
                )

                Box {
                    FilledTonalIconButton(
                        onClick = { showSortMenu = true }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Sort,
                            contentDescription = "Trier par"
                        )
                    }

                    DropdownMenu(
                        expanded = showSortMenu,
                        onDismissRequest = { showSortMenu = false }
                    ) {
                        Text(
                            text = "Trier par",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                        HorizontalDivider()
                        TaskSortOption.entries.forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = option.displayName,
                                        fontWeight = if (option == sortOption) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                onClick = {
                                    sortOption = option
                                    showSortMenu = false
                                },
                                leadingIcon = {
                                    if (option == sortOption) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        if (uncompletedTasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Rounded.Checklist,
                        contentDescription = "Aucune tâche à faire",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Aucune tâche à faire !",
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
        } else if (filteredAndSortedTasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.SearchOff,
                        contentDescription = "Aucun résultat",
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Aucune tâche ne correspond à votre recherche",
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        } else {
            SegmentedListGroup(
                items = filteredAndSortedTasks,
                onItemActionClick = {
                    taskForPhotoChoice = it
                    launchCamera(it)
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    taskForPhotoChoice?.let { task ->
        AlertDialog(
            onDismissRequest = { taskForPhotoChoice = null },
            title = { Text("Ajouter une preuve") },
            text = { Text("D'ou viens ce chef-d'oeuvre ?") },
            confirmButton = {
                Button(onClick = {
                    taskForPhotoChoice = null
                    launchCamera(task)
                }) {
                    Text("Appareil photo")
                }
            },
            dismissButton = {
                FilledTonalButton(onClick = {
                    taskForPhotoChoice = null
                    galleryLauncher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
                }) {
                    Text("Galerie")
                }
            }
        )
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(MaterialTheme.shapes.medium),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "C'est bon, on valide l'activité ?",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    isUploading = true
                    coroutineScope.launch {
                        try {
                            val taskToUpdate = currentTask ?: return@launch

                            val storageRef = storage.reference.child("souvenirs/${taskToUpdate.id}.jpg")
                            storageRef.putFile(photoUri!!).await()

                            val downloadUrl = storageRef.downloadUrl.await().toString()

                            val finalTask = taskToUpdate.copy(
                                photoUri = downloadUrl,
                                completed = true,
                                completedAt = System.currentTimeMillis()
                            )

                            db.collection("tasks").document(finalTask.id).set(finalTask).await()

                            val index = tasks.indexOf(taskToUpdate)
                            if (index != -1) {
                                tasks[index] = finalTask
                            }

                            showConfirmDialog = false
                        } catch (e: Exception) {
                            Toast.makeText(context, "Erreur de serveur !!", Toast.LENGTH_SHORT).show()
                            Log.i("Erreur firebase: ", "${e.message}")
                        } finally {
                            isUploading = false
                        }
                    }
                }) {
                    if (isUploading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Carrément !")
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }, enabled = !isUploading) { Text("Bof, on la refait") }
            }
        )
    }
}
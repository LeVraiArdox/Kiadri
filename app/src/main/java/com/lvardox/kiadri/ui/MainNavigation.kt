package com.lvardox.kiadri.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

import com.lvardox.kiadri.models.Task
import com.lvardox.kiadri.ui.screens.HomeScreen

import com.lvardox.kiadri.ui.components.AddTaskDialog
import com.lvardox.kiadri.ui.screens.HistoryScreen

@Composable
fun MainNavigation() {
    var currentScreen by remember { mutableStateOf("home") }
    val appIcons = Icons.Rounded
    val tasks = remember { mutableStateListOf<Task>() }
    var showDialog by remember { mutableStateOf(false) }
    val db = Firebase.firestore
    var isLoading by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        db.collection("tasks").addSnapshotListener {
            snapshot, error ->
            isLoading = false
            if (error != null) {
                println("Erreur firebase: ${error.message}")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                tasks.clear()
                for (document in snapshot.documents) {
                    val task = document.toObject(Task::class.java)
                    if (task != null) {
                        tasks.add(task)
                    }
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentScreen == "home",
                    onClick = { currentScreen = "home"},
                    icon = { Icon(appIcons.Checklist, "En cours") },
                    label = { Text("En cours") }
                )
                NavigationBarItem(
                    selected = currentScreen == "history",
                    onClick = { currentScreen = "history"},
                    icon = { Icon(appIcons.Image, "Souvenirs") },
                    label = { Text("Souvenirs") }
                )
            }
        },
        floatingActionButton = {
            if (currentScreen == "home") {
                FloatingActionButton(
                    onClick = { showDialog = true },
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(appIcons.Add, contentDescription = "Ajouter")
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            } else {
                if (currentScreen == "home") {
                    HomeScreen(tasks)
                } else {
                    HistoryScreen(tasks.filter { it.completed })
                }
            }
        }
        if (showDialog) {
            AddTaskDialog(
                onDismiss = {showDialog = false},
                onConfirm = { title ->
                    val newTask = Task(title = title)
                    db.collection("tasks")
                        .document(newTask.id)
                        .set(newTask)
                        .addOnFailureListener { e ->
                            println("Impossible d'ajouter la tâche: ${e.message}")
                        }

                    showDialog = false
                }
            )
        }
    }
}
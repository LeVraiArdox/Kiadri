package com.lvardox.kiadri.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import com.lvardox.kiadri.KiadriTheme
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

    Scaffold(
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
            if (currentScreen == "home") {
                HomeScreen(tasks)
            } else {
                HistoryScreen(tasks.filter { it.isCompleted })
            }
        }
        if (showDialog) {
            AddTaskDialog(
                onDismiss = {showDialog = false},
                onConfirm = { title ->
                    tasks.add(Task(title = title))
                    showDialog = false
                }
            )
        }
    }
}
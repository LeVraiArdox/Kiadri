package com.lvardox.kiadri.ui.screens

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
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.HideImage
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lvardox.kiadri.models.Task
import com.lvardox.kiadri.ui.components.ScrapbookCard

@Composable
fun HistoryScreen(completedTasks: List<Task>) {
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
                ScrapbookCard(task)
            }
        }
    }
}
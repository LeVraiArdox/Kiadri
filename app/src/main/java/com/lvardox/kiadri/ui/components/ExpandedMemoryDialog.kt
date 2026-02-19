package com.lvardox.kiadri.ui.components

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.SubcomposeAsyncImage
import com.lvardox.kiadri.models.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.text.format
import androidx.core.net.toUri

@Composable
fun ExpandedMemoryDialog(task: Task, onDismiss: () -> Unit, onDelete: (Task) -> Unit) {
    val context = LocalContext.current
    var showFullScreen by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    var dateStr = task.completedAt?.let {
        SimpleDateFormat("dd MMMM yyyy 'à' HH:mm", Locale.FRANCE).format(Date(it))
    } ?: "Date inconnue"

    if (showFullScreen) {
        Dialog(
            onDismissRequest = { showFullScreen = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                SubcomposeAsyncImage(
                    model = task.photoUri,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize(),
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                )

                IconButton(
                    onClick = { showFullScreen = false },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .statusBarsPadding()
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Fermer", tint = Color.White)
                }
            }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    model = task.photoUri,
                    contentDescription = task.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable { showFullScreen = true }
                        .clip(MaterialTheme.shapes.medium),
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.size(24.dp))

                Text(
                    text = task.title,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Validé le $dateStr",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilledTonalIconButton(
                        onClick = {
                            val request = DownloadManager.Request(task.photoUri?.toUri())
                                .setTitle(task.title)
                                .setDescription("Téléchargement de l'image de ${task.title}")
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setDestinationInExternalPublicDir(
                                    Environment.DIRECTORY_PICTURES,
                                    "Kiadri_${System.currentTimeMillis()}.jpg"
                                )

                            val manager =
                                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                            manager.enqueue(request)
                        }
                    ) {
                        Icon(Icons.Default.Download, contentDescription = "Télécharger")
                    }

                    FilledTonalIconButton(
                        onClick = { showDeleteConfirmation = true },
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            contentColor = MaterialTheme.colorScheme.error,
                        )
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Supprimer")
                    }
                }
            }
        }
    }

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Supprimer l'image ?") },
            text = { Text("C'est définitif. La photo sera pulvérisée des serveurs à tout jamais. Tu es sûr de ton coup ?") },
            confirmButton = {
                Button(
                    onClick = {
                        showDeleteConfirmation = false
                        onDelete(task)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                    )
                ) {
                    Text("Adieu")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text("Non")
                }
            }
        )
    }
}
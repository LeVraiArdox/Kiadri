package com.lvardox.kiadri.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color

enum class SegmentPosition {
    TOP, MIDDLE, BOTTOM, SINGLE
}

@Composable
fun SegmentedListItem(
    title: String,
    leadingIcon: ImageVector,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    position: SegmentPosition = SegmentPosition.SINGLE,
    onRowClick: (() -> Unit)? = null,
    onSwipeRight: (() -> Unit)? = null
) {
    val cornerRadius = 24.dp
    val smallRadius = 6.dp
    val shape = when (position) {
        SegmentPosition.TOP -> RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius, bottomStart = smallRadius, bottomEnd = smallRadius)
        SegmentPosition.BOTTOM -> RoundedCornerShape(bottomStart = cornerRadius, bottomEnd = cornerRadius, topStart = smallRadius, topEnd = smallRadius)
        SegmentPosition.MIDDLE -> RoundedCornerShape(smallRadius)
        SegmentPosition.SINGLE -> RoundedCornerShape(cornerRadius)
    }

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            it == SwipeToDismissBoxValue.StartToEnd
        }
    )

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.StartToEnd) {
            onSwipeRight?.invoke()
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier.fillMaxWidth(),
        enableDismissFromEndToStart = false,
        enableDismissFromStartToEnd = onSwipeRight != null,
        backgroundContent = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.errorContainer
                    else -> MaterialTheme.colorScheme.surfaceContainerHigh
                }
            )
            val scale by animateFloatAsState(
                if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) 1f else 0.8f
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color, shape)
                    .padding(start = 24.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = "Supprimer",
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.scale(scale)
                )
            }
        },
        content = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = shape,
                color = MaterialTheme.colorScheme.surfaceContainerHigh
            ) {
                ListItem(
                    modifier = Modifier.then(
                        if (onRowClick != null) Modifier.clickable { onRowClick() } else Modifier
                    ),
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent
                    ),
                    leadingContent = {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    headlineContent = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    trailingContent = {
                        FilledIconButton(
                            onClick = onButtonClick,
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(
                                Icons.Rounded.CameraAlt,
                                contentDescription = "Valider",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                )
            }
        }
    )
}
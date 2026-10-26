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
    onRowClick: (() -> Unit)? = null
) {
    val cornerRadius = 24.dp
    val smallRadius = 6.dp
    val shape = when (position) {
        SegmentPosition.TOP -> RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius, bottomStart = smallRadius, bottomEnd = smallRadius)
        SegmentPosition.BOTTOM -> RoundedCornerShape(bottomStart = cornerRadius, bottomEnd = cornerRadius, topStart = smallRadius, topEnd = smallRadius)
        SegmentPosition.MIDDLE -> RoundedCornerShape(smallRadius)
        SegmentPosition.SINGLE -> RoundedCornerShape(cornerRadius)
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        color = MaterialTheme.colorScheme.surfaceContainerHigh
    ) {
        ListItem(
            modifier = Modifier.then(
                if (onRowClick != null) Modifier.clickable { onRowClick() } else Modifier
            ),
            colors = ListItemDefaults.colors(
                containerColor = androidx.compose.ui.graphics.Color.Transparent
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
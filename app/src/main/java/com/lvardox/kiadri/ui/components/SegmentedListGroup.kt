package com.lvardox.kiadri.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lvardox.kiadri.models.Task

@Composable
fun SegmentedListGroup(
    items: List<Task>,
    onItemActionClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) return

    Column(modifier = modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
    ) {
        items.forEachIndexed { index, item ->
            val position = when {
                items.size == 1 -> SegmentPosition.SINGLE
                index == 0 -> SegmentPosition.TOP
                index == items.lastIndex -> SegmentPosition.BOTTOM
                else -> SegmentPosition.MIDDLE
            }

            SegmentedListItem(
                title = item.title,
                leadingIcon = Icons.Rounded.Check,
                position = position,
                onButtonClick = { onItemActionClick(item) }
            )

            if (index < items.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(start = 2.dp),
                    color = Color.Transparent,
                    thickness = 2.dp
                )
            }
        }
    }
}
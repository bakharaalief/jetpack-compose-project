package com.bakhdev.composefirstproject.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ContentBody(listState: LazyListState, listItem: List<String>, firstItemTranslationY: Float) {
    LazyColumn(
        state = listState
    ) {
        item {
            ExpandedTopBar(
                firstItemTranslationY = firstItemTranslationY
            ) {

            }
        }
        items(items = listItem) { item ->
            Text(
                text = item, modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp)
            )
        }
    }
}
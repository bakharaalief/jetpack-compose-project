package com.bakhdev.composefirstproject.presentation.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.bakhdev.composefirstproject.presentation.home.component.CollapsedTopBar
import com.bakhdev.composefirstproject.presentation.home.component.ContentBody
import com.bakhdev.composefirstproject.presentation.home.component.collapsedHeight
import com.bakhdev.composefirstproject.presentation.home.component.expandedHeight
import com.bakhdev.composefirstproject.ui.theme.ComposefirstprojectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposefirstprojectTheme {
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun DefaultPreview() {
    ComposefirstprojectTheme {
//        CollapsedTopBar(isCollapsed = true)
    }
}

@Composable
private fun HomeScreen() {
    val listState = rememberLazyListState()
    val overlapHeightPx = with(LocalDensity.current) {
        expandedHeight.toPx() - collapsedHeight.toPx()
    }
    val topAppBarCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden = listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }
    val firstItemTranslationY by remember {
        derivedStateOf {
            when {
                listState.layoutInfo.visibleItemsInfo.isNotEmpty() && listState.firstVisibleItemIndex == 0 -> listState.firstVisibleItemScrollOffset * .6f
                else -> 0f
            }
        }
    }
    val listPostData = arrayListOf<String>().apply {
        for (i in 0..40) add("Item ke $i")
    }

    Box {
        CollapsedTopBar(
            isCollapsed = topAppBarCollapsed,
            onMenuClick = {},
            onProfileClick = {},
            onFormValueChange = {}
        )

        ContentBody(
            listState = listState,
            listItem = listPostData,
            firstItemTranslationY = firstItemTranslationY
        )
    }
}
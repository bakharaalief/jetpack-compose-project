package com.bakhdev.composefirstproject.presentation.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.bakhdev.composefirstproject.helper.createDummyListData
import com.bakhdev.composefirstproject.presentation.home.component.CollapsedTopBar
import com.bakhdev.composefirstproject.presentation.home.component.ExpandedTopBar
import com.bakhdev.composefirstproject.presentation.home.component.collapsedHeight
import com.bakhdev.composefirstproject.presentation.home.component.expandedHeight
import com.bakhdev.composefirstproject.ui.theme.ComposefirstprojectTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TransparentSystemBars()
            HomeScreen()
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
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent, darkIcons = useDarkIcons
        )

        onDispose {}
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
    val listPostData = createDummyListData()
    var textInput by remember { mutableStateOf("") }
    val userName by remember { mutableStateOf("Bakhara Alief") }
    val quotes by remember { mutableStateOf("Start new advanture") }

    Box {
        //collapsed header
        CollapsedTopBar(isCollapsed = topAppBarCollapsed,
            onMenuClick = {},
            onProfileClick = {},
            formValue = textInput,
            userName = userName,
            quotes = quotes,
            onFormValueChange = { formInput ->
                textInput = formInput
            })

        //under body
        LazyColumn(
            state = listState,
        ) {
            item {
                ExpandedTopBar(
                    firstItemTranslationY = firstItemTranslationY,
                    userName = userName,
                    quotes = quotes,
                    formValue = textInput
                ) { formInput ->
                    textInput = formInput
                }
            }
            items(items = listPostData) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(10.dp)
                )
            }
        }
    }
}
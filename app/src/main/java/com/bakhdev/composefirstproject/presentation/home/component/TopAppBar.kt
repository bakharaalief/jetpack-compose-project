package com.bakhdev.composefirstproject.presentation.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.bakhdev.composefirstproject.R
import com.bakhdev.composefirstproject.isVisible

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchForm(modifier: Modifier, onFormValueChange: (String) -> Unit) {
    TextField(
        value = "",
        placeholder = {
            Row {
                Icon(
                    imageVector = Icons.Filled.LocationOn, contentDescription = ""
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(text = "Where are you going?")
            }
        },
        onValueChange = onFormValueChange,
        shape = CircleShape,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

val spacerHeight = 40.dp

@Composable
fun SpacerConnectAppBarAndBody(modifier: Modifier) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(spacerHeight)
    )
}

@Composable
fun BackgroundImage(modifier: Modifier, url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.ic_launcher_background)
    )
}

val expandedHeight = 500.dp
const val defaultImage =
    "https://images.unsplash.com/photo-1590930754517-64d5fffa06ac?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1587&q=80"
const val defaultImage2 =
    "https://images.unsplash.com/photo-1555899434-94d1368aa7af?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80"

@Composable
fun ExpandedTopBar(firstItemTranslationY: Float, onFormValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(expandedHeight)
    ) {

        //image for background
        BackgroundImage(
            modifier = Modifier
                .height(expandedHeight)
                .graphicsLayer {
                    translationY = firstItemTranslationY
                },
            url = defaultImage,
        )

        //white box to connect the top appbar and body content
        SpacerConnectAppBarAndBody(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(Color.White)
        )

        //search form
        SearchForm(
            modifier = Modifier.align(Alignment.BottomEnd), onFormValueChange = onFormValueChange
        )
    }
}

val collapsedHeight = 300.dp

@Composable
fun CollapsedTopBar(
    isCollapsed: Boolean,
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit,
    onFormValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(2f)
            .background(Color.Transparent)
            .height(collapsedHeight),
    ) {

        AnimatedVisibility(
            visible = isCollapsed, modifier = Modifier,
            enter = slideInVertically(
                initialOffsetY = { -100 },
                animationSpec = tween(durationMillis = 500)
            ), exit = slideOutVertically(
                targetOffsetY = { 1 },
                animationSpec = tween(durationMillis = 500)
            )
        ) {

            Box {
                //image for background
                BackgroundImage(
                    modifier = Modifier
                        .isVisible(isCollapsed)
                        .height(collapsedHeight),
                    url = defaultImage2,
                )
            }
        }

        //menu nih
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //menu icon
            Button(
                onClick = onMenuClick,
                shape = CircleShape,
                contentPadding = PaddingValues(1.dp),
                modifier = Modifier.size(50.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }

            //profile icon
            Button(
                onClick = onProfileClick,
                shape = CircleShape,
                contentPadding = PaddingValues(1.dp),
                modifier = Modifier.size(50.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        //spacer for connecting appbar and body
        SpacerConnectAppBarAndBody(
            modifier = Modifier
                .background(if (isCollapsed) Color.White else Color.Transparent)
                .align(Alignment.BottomEnd)
        )

        //search form
        SearchForm(
            modifier = Modifier
                .isVisible(isCollapsed)
                .align(Alignment.BottomEnd),
            onFormValueChange = onFormValueChange
        )
    }
}
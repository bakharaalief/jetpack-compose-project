package com.bakhdev.composefirstproject.presentation.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.bakhdev.composefirstproject.R
import com.bakhdev.composefirstproject.helper.isVisible

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchForm(
    modifier: Modifier,
    isVisible: Boolean,
    formValue: String,
    onFormValueChange: (String) -> Unit,
) {
    TextField(
        value = formValue,
        placeholder = {
            Row {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "",
                    tint = Color(0xFFF59542)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(text = "Where are you going?")
            }
        },
        onValueChange = onFormValueChange,
        modifier = modifier
            .fillMaxWidth()
            .isVisible(isVisible)
            .padding(20.dp)
            .shadow(8.dp, shape = CircleShape)
            .clip(CircleShape),
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

@Composable
fun TextInsideTopAppBar(name: String, quotes: String, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Good Morning, $name",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Text(
            text = quotes, color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TopAppBarButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(1.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp
        ),
        modifier = Modifier.size(50.dp),
        content = content
    )
}

val expandedHeight = 600.dp
const val defaultImage =
    "https://images.unsplash.com/photo-1584660470766-20ac1a28c7fe?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1587&q=80"
const val defaultImage2 =
    "https://images.unsplash.com/photo-1569254994521-ddbb54af5ae8?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1624&q=80"
const val profileImage =
    "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"

@Composable
fun ExpandedTopBar(
    firstItemTranslationY: Float,
    userName: String,
    quotes: String,
    formValue: String,
    onFormValueChange: (String) -> Unit,
) {
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

        TextInsideTopAppBar(
            name = userName,
            quotes = quotes,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 100.dp)
        )

        //search form
        SearchForm(
            modifier = Modifier.align(Alignment.BottomEnd),
            isVisible = true,
            formValue = formValue,
            onFormValueChange = onFormValueChange,
        )
    }
}

val collapsedHeight = 450.dp

@Composable
fun CollapsedTopBar(
    isCollapsed: Boolean,
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit,
    userName: String,
    quotes: String,
    formValue: String,
    onFormValueChange: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(2f)
            .background(Color.Transparent)
            .height(collapsedHeight),
    ) {

        AnimatedVisibility(
            visible = isCollapsed, modifier = Modifier, enter = slideInVertically(
                initialOffsetY = { -100 }, animationSpec = tween(durationMillis = 500)
            ), exit = slideOutVertically(
                targetOffsetY = { 1 }, animationSpec = tween(durationMillis = 500)
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
                .safeDrawingPadding()
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.TopCenter), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //menu icon
            TopAppBarButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp),
                )
            }

            //profile icon
            TopAppBarButton(onClick = onProfileClick) {
                AsyncImage(
                    model = profileImage,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_launcher_background)
                )
            }
        }

        //spacer for connecting appbar and body
        SpacerConnectAppBarAndBody(
            modifier = Modifier
                .background(if (isCollapsed) Color.White else Color.Transparent)
                .align(Alignment.BottomEnd)
        )

        //text inside
        TextInsideTopAppBar(
            name = userName,
            quotes = quotes,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .isVisible(isCollapsed)
                .padding(start = 20.dp, bottom = 100.dp)
        )

        //search form
        SearchForm(
            modifier = Modifier
                .isVisible(isCollapsed)
                .align(Alignment.BottomEnd),
            isVisible = isCollapsed,
            formValue = formValue,
            onFormValueChange = onFormValueChange,
        )
    }
}
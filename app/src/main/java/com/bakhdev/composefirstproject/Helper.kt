package com.bakhdev.composefirstproject

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

fun Modifier.isVisible(boolean: Boolean): Modifier = this.alpha(if (boolean) 1f else 0f)
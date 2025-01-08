package com.example.pants.presentation.components.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PantsAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}
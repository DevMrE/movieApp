package com.kmp.movieapp.core.presentation.material

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

fun Shapes.ultraLarge(): CornerBasedShape = ShapeDefaults.ExtraLarge.copy(CornerSize(42.dp))
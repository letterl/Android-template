package com.zran.sloth.entity

import androidx.compose.runtime.Composable

data class BarEntity(
  var title: String,
  var icon: @Composable () -> Unit,
  var selected: Int,
)

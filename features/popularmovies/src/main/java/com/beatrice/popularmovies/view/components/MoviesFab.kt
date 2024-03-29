package com.beatrice.popularmovies.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.beatrice.popularmovies.R

@Composable
fun MoviesFab(
    scrollUp: () -> Unit
) {
    FloatingActionButton(
        modifier = Modifier.padding(16.dp),
        onClick = scrollUp,
        shape = CircleShape
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "scroll to top")
    }
}

package com.example.mainmenu.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(widthDp = 320, heightDp = 320)
@Composable
fun MainMenuScreen() {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Text(text = "MainMenuScreen", style = MaterialTheme.typography.body1)
		Button(onClick = { /*TODO*/ }) {
			
		}
	}
}
package com.example.timetable_for_hits.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PreviewMainActivity()
		}
	}
}

@Preview
@Composable
fun PreviewMainActivity() {
	Box {
		Text(text = "hello, timetable!!!")
	}
}

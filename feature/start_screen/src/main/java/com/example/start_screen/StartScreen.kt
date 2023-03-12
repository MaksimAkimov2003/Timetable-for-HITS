package com.example.start_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resources.theme.TimetableTheme
import com.example.shared.choosing_screens.ui.ScreenTypes

private val localTextTitle = TextStyle(
	fontSize = 14.sp,
	fontWeight = FontWeight.SemiBold,
	color = Color(0xFFFFFFFF)
)

val localTextBody = TextStyle(
	fontSize = 12.sp,
	fontWeight = FontWeight.Medium,
	color = Color(0xFFFFFFFF)
)

@Preview
@Composable
fun StartScreen() {
	TimetableTheme() {
		Column(
			modifier = Modifier
				.background(color = MaterialTheme.colors.background)
				.fillMaxSize()
		) {
			Text(
				text = ScreenTypes.StartScreen.title,
				style = MaterialTheme.typography.subtitle1,
				modifier = Modifier
					.padding(start = 24.dp, top = 36.dp)
			)

			Text(
				text = ScreenTypes.StartScreen.subtitle,
				style = MaterialTheme.typography.body1,
				modifier = Modifier
					.padding(start = 24.dp, top = 8.dp)
			)

			Row(
				modifier = Modifier
					.padding(top = 40.dp, start = 24.dp, end = 24.dp)
					.background(
						color = MaterialTheme.colors.primaryVariant,
						shape = RoundedCornerShape(10.dp)
					)
					.fillMaxWidth()
					.height(80.dp)
			)
			{
				val painter = painterResource(id = R.drawable.student)

				Image(
					painter = painter,
					contentDescription = null,
					modifier = Modifier
						.padding(start = 16.dp, bottom = 16.dp)
						.width(80.dp)
						.requiredHeight(80.dp)
				)

				Column(
					modifier = Modifier
						.padding(start = 8.dp)
						.fillMaxSize()
				) {
					Text(
						modifier = Modifier.padding(top = 16.dp),
						text = "Student",
						style = localTextTitle
					)

					Text(
						modifier = Modifier.padding(top = 4.dp),
						text = "Your class schedule will always be at hand",
						style = localTextBody
					)
				}

			}

			Row(
				modifier = Modifier
					.padding(top = 24.dp, start = 24.dp, end = 24.dp)
					.background(
						color = MaterialTheme.colors.primary,
						shape = RoundedCornerShape(10.dp)
					)
					.fillMaxWidth()
					.height(80.dp)
			)
			{
				val painter = painterResource(id = R.drawable.teacher)

				Image(
					painter = painter,
					contentDescription = null,
					modifier = Modifier
						.padding(start = 16.dp, bottom = 16.dp)
						.requiredHeight(80.dp)
						.width(80.dp)
				)

				Column(
					modifier = Modifier
						.padding(start = 8.dp)
						.fillMaxSize()
				) {
					Text(
						modifier = Modifier.padding(top = 16.dp),
						text = "Teacher",
						style = localTextTitle
					)

					Text(
						modifier = Modifier.padding(top = 4.dp),
						text = "Let’s show in which groups you teach and at what time",
						style = localTextBody
					)
				}

			}

			Box(
				modifier = Modifier
					.padding(top = 16.dp)
					.fillMaxSize(),
				contentAlignment = Alignment.BottomEnd
			) {
				Image(
					painter = painterResource(id = R.drawable.bottom_image),
					contentDescription = null,
				)
			}

		}
	}
}
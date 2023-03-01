package com.example.mainmenu.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mainmenu.R
import com.example.resources.theme.TimetableTheme
import com.example.resources.theme.mainMenuBorder

@Preview(widthDp = 500, heightDp = 1000)
@Composable
fun MainMenuScreen() {
	val itemsMap = remember {
		createItemsMap()
	}

	val footerPainter = painterResource(id = com.example.core.resources.R.drawable.big_cirles)

	TimetableTheme() {
		CreateToolbar()

		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = 56.dp)
				.background(color = MaterialTheme.colors.background)
		) {
			for (item in itemsMap) {
				ListItem(text = item.key, imageId = item.value)
			}

			Image(
				painter = footerPainter,
				contentDescription = null,
				modifier = Modifier.padding(top = 16.dp)
			)
		}
	}
}

@Composable
private fun ListItem(
	text: String,
	imageId: Int
) {
	val iconPainter = painterResource(id = imageId)
	val buttonPainter = painterResource(id = R.drawable.item_button)

	TimetableTheme() {

		Row(
			modifier = Modifier
				.padding(
					top = 40.dp,
					start = 16.dp,
					end = 16.dp,
				)
				.background(
					shape = RoundedCornerShape(10.dp),
					color = MaterialTheme.colors.background
				)
				.border(
					width = 3.dp,
					color = mainMenuBorder,
					shape = RoundedCornerShape(10.dp)
				)
				.clickable { navigateAfterClickOnItem() }
				.wrapContentHeight()
				.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				painter = iconPainter,
				contentDescription = null,
				modifier = Modifier
					.padding(
						start = 16.dp,
						top = 16.dp,
						bottom = 16.dp,
						end = 0.dp,
					)
			)

			Text(
				text = text,
				style = MaterialTheme.typography.body1,
				modifier = Modifier.padding(
					start = 16.dp,
					top = 16.dp,
					bottom = 16.dp,
					end = 0.dp,
				)
			)

			Box(
				contentAlignment = Alignment.CenterEnd,
				modifier = Modifier
					.fillMaxWidth()
			) {
				IconButton(
					onClick = { navigateAfterClickOnItem() },
					modifier = Modifier
						.paint(buttonPainter)
						.padding(end = 16.dp)
				) {
				}
			}

		}
	}

}

@Preview(widthDp = 500, heightDp = 100)
@Composable
private fun CreateToolbar() {
	val painter = painterResource(id = com.example.core.resources.R.drawable.small_circles)
	val buttonPainter = painterResource(id = com.example.core.resources.R.drawable.back_button)

	TimetableTheme() {
		Row(
			modifier = Modifier
				.background(color = MaterialTheme.colors.background)
				.fillMaxWidth()
				.wrapContentHeight()
				.padding(top = 16.dp)
		) {
			IconButton(
				onClick = { /*TODO("Обработать нажатие на кнопку "назад"")*/ },
				modifier = Modifier
					.paint(buttonPainter)
					.padding(start = 16.dp)
			) {
			}

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight(),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = "Меню",
					style = MaterialTheme.typography.h5,
					modifier = Modifier.padding(
						end = 4.dp,
						top = 8.dp
					)
				)

				Image(
					painter = painter,
					contentDescription = null,
				)
			}
		}
	}
}

private fun createItemsMap(): Map<String, Int> = mapOf(
	"Группы" to R.drawable.item_groups,
	"Преподаватели" to R.drawable.item_teachers,
	"Избранное" to R.drawable.item_heart,
	"Аудитории" to R.drawable.item_auditories,
	"Настройки" to R.drawable.item_settings,
	"Удалить данные" to R.drawable.item_delete_data
)

private fun navigateAfterClickOnItem() {
	Log.e("TEST", "clicked")
}
package com.example.mainmenu.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resources.theme.TimetableTheme
import com.example.resources.theme.Typography
import com.example.resources.theme.background
import com.example.resources.theme.mainMenuBorder

@Preview(widthDp = 500, heightDp = 1000)
@Composable
fun MainMenuScreen() {
	val itemsMap = remember {
		createItemsMap()
	}
	TimetableTheme() {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.padding(
					start = 16.dp,
					end = 16.dp,
				)
		) {
			for (item in itemsMap) {
				ListItem(text = item.key, imageId = item.value)
			}
		}
	}
}

@Composable
private fun ListItem(
	text: String,
	imageId: Int
) {
	val iconPainter = painterResource(id = imageId)
	val buttonPainter = painterResource(id = com.example.mainmenu.R.drawable.item_button)

	TimetableTheme() {

		Row(
			modifier = Modifier
				.padding(
					top = 40.dp
				)
				.background(
					shape = RoundedCornerShape(10.dp),
					color = background
				)
				.border(
					width = 3.dp,
					color = mainMenuBorder,
					shape = RoundedCornerShape(10.dp)
				)
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
				style = Typography.body1,
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
					onClick = { /*TODO*/ },
					modifier = Modifier
						.paint(buttonPainter)
						.padding(end = 16.dp)
				) {
				}
			}

		}
	}
}

private fun createItemsMap(): Map<String, Int> = mapOf(
	"Группы" to com.example.mainmenu.R.drawable.item_groups,
	"Преподаватели" to com.example.mainmenu.R.drawable.item_teachers,
	"Избранное" to com.example.mainmenu.R.drawable.item_heart,
	"Аудитории" to com.example.mainmenu.R.drawable.item_auditories,
	"Настройки" to com.example.mainmenu.R.drawable.item_settings,
	"Удалить данные" to com.example.mainmenu.R.drawable.item_delete_data
)
package com.example.mainmenu.ui

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mainmenu.R
import com.example.mainmenu.presentation.MainMenuViewModel
import com.example.resources.theme.TimetableTheme
import com.example.resources.theme.mainMenuBorder
import com.example.screens.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainMenuScreen(
	mainMenuViewModel: MainMenuViewModel = koinViewModel(),
	navController: NavController
) {
	val itemsMap = remember {
		createItemsMap()
	}

	val footerPainter = painterResource(id = com.example.core.resources.R.drawable.big_cirles)

	TimetableTheme() {
		CreateToolbar(navController)

		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = 56.dp)
				.background(color = MaterialTheme.colors.background)
		) {
			for (item in itemsMap) {
				ListItem(text = item.key, imageId = item.value, navController, mainMenuViewModel)
			}

			Box(
				modifier = Modifier
					.padding(top = 16.dp)
					.fillMaxSize(),
				contentAlignment = Alignment.BottomStart
			) {
				Image(
					painter = footerPainter,
					contentDescription = null,
				)
			}
		}
	}
}

@Composable
private fun ListItem(
	text: String,
	imageId: Int,
	navController: NavController,
	mainMenuViewModel: MainMenuViewModel
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
				.clickable { navigateAfterClickOnItem(text, navController, mainMenuViewModel) }
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
					onClick = { navigateAfterClickOnItem(text, navController, mainMenuViewModel) },
					modifier = Modifier
						.paint(buttonPainter)
						.padding(end = 16.dp)
				) {
				}
			}

		}
	}

}

@Composable
private fun CreateToolbar(navController: NavController) {
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
				onClick = { navController.navigate(Screen.WeekTimetableScreen.route) },
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

private fun navigateAfterClickOnItem(text: String, navController: NavController, mainMenuViewModel: MainMenuViewModel) {
	when (text) {
		"Группы"         -> navController.navigate(Screen.FacultiesScreen.route)
		"Преподаватели"  -> navController.navigate(Screen.TeachersScreen.route)
		"Аудитории"      -> navController.navigate(Screen.AuditoriesScreen.route)

		"Удалить данные" -> {
			mainMenuViewModel.deleteData()
			navController.navigate(Screen.StartScreen.route)
		}

		else             -> Unit
	}
}
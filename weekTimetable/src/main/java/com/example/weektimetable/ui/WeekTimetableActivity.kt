package com.example.weektimetable.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resources.theme.TimetableTheme
import com.example.weektimetable.presentation.WeekTimetableViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private val data: List<MyDay> = listOf(
	MyDay("Понедельник", listOf(
		MySchedule("вода", "8:45\n10:20"),
		MySchedule("чай", "10:35\n12:10"),
		MySchedule("кофэ", "12:25\n14:00"),
		MySchedule("сок", "14:45\n16:20"),
		MySchedule("пиво", "16:35\n18:10"),
		MySchedule("ром", "18:25\n18:20"),
		MySchedule("туалет", "20:15\n21:50")
	)),
	MyDay("Вторник", listOf(
		MySchedule("прыгит", "8:45\n10:20"),
		MySchedule("анжумания", "10:35\n12:10"),
		MySchedule("подтягивания", "12:25\n14:00"),
		MySchedule("корейский бег на руках", "14:45\n16:20"),
		MySchedule("отдых", "16:35\n18:10"),
		MySchedule("жим стоя", "18:25\n18:20"),
		MySchedule("жим на бегу", "20:15\n21:50")
	)),
	MyDay("Среда", listOf(
		MySchedule("грусть", "8:45\n10:20"),
		MySchedule("тоска", "10:35\n12:10"),
		MySchedule("воодушевление", "12:25\n14:00"),
		MySchedule("депрессия", "14:45\n16:20"),
		MySchedule("ужин", "16:35\n18:10"),
		MySchedule("сон", "18:25\n18:20"),
		MySchedule("депрессия", "20:15\n21:50")
	)),
	MyDay("Четверг", listOf(
		MySchedule("грусть", "8:45\n10:20"),
		MySchedule("тоска", "10:35\n12:10"),
		MySchedule("воодушевление", "12:25\n14:00"),
		MySchedule("депрессия", "14:45\n16:20"),
		MySchedule("ужин", "16:35\n18:10"),
		MySchedule("сон", "18:25\n18:20"),
		MySchedule("депрессия", "20:15\n21:50")
	)),
	MyDay("Пятница", listOf(
		MySchedule("грусть", "8:45\n10:20"),
		MySchedule("тоска", "10:35\n12:10"),
		MySchedule("воодушевление", "12:25\n14:00"),
		MySchedule("депрессия", "14:45\n16:20"),
		MySchedule("ужин", "16:35\n18:10"),
		MySchedule("сон", "18:25\n18:20"),
		MySchedule("депрессия", "20:15\n21:50")
	)),
	MyDay("Суббота", listOf(
		MySchedule("грусть", "8:45\n10:20"),
		MySchedule("тоска", "10:35\n12:10"),
		MySchedule("воодушевление", "12:25\n14:00"),
		MySchedule("депрессия", "14:45\n16:20"),
		MySchedule("ужин", "16:35\n18:10"),
		MySchedule("сон", "18:25\n18:20"),
		MySchedule("депрессия", "20:15\n21:50"))),
	MyDay("Воскресенье", listOf(
		MySchedule("грусть", "8:45\n10:20"),
		MySchedule("тоска", "10:35\n12:10"),
		MySchedule("воодушевление", "12:25\n14:00"),
		MySchedule("депрессия", "14:45\n16:20"),
		MySchedule("ужин", "16:35\n18:10"),
		MySchedule("сон", "18:25\n18:20"),
		MySchedule("депрессия", "20:15\n21:50")))
)

class WeekTimetableActivity: ComponentActivity() {

	private val viewModel: WeekTimetableViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			TimetableTheme {
				Box(modifier = Modifier
					.background(MaterialTheme.colors.secondary)
					.fillMaxSize()) {
					Box(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 32.dp)) {
						DrawContent()
					}
				}
			}
		}
	}

	@Composable
	private fun DrawContent() {
		Column {
			Box(modifier = Modifier
				.wrapContentSize()
				.padding(bottom = 8.dp)
				.clip(MaterialTheme.shapes.medium)
				.background(MaterialTheme.colors.background)
				.align(Alignment.CenterHorizontally),
				contentAlignment = Alignment.Center) {
				DrawTitleBar()
			}
			Box(modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 8.dp)
				.clip(MaterialTheme.shapes.medium)
				.background(MaterialTheme.colors.background),
				contentAlignment = Alignment.Center) {
				DrawDateBar()
			}
			Box(modifier = Modifier
				.fillMaxSize()
				.clip(MaterialTheme.shapes.medium)
				.background(MaterialTheme.colors.background)) {
				ScrollTable(TimetableAdapter(data))
//				Table()
			}
		}
	}

	@Composable
	private fun DrawTitleBar() {
		Column(modifier = Modifier.padding(32.dp, 4.dp, 32.dp, 4.dp)) {
			Text(text = "Group 972102", style = MaterialTheme.typography.body1, modifier = Modifier.align(Alignment.CenterHorizontally))
			Text(text = "February 2023 * 23 week", style = MaterialTheme.typography.body2, modifier = Modifier.align(Alignment.CenterHorizontally))
		}
	}

	@Composable
	private fun DrawDateBar() {
		Row(modifier = Modifier.padding(4.dp, 4.dp, 4.dp, 4.dp)) {
			IconButton(onClick = { /*TODO*/ }) {
				Icon(
					painter = painterResource(id = com.example.core.resources.R.drawable.arrow_to_left),
					contentDescription = "",
					tint = MaterialTheme.colors.primary
				)
			}
			Text(text = "20 February - 26 February",
				 style = MaterialTheme.typography.body1,
				 modifier = Modifier.align(Alignment.CenterVertically))
			IconButton(onClick = { /*TODO*/ },) {
				Icon(
					painter = painterResource(id = com.example.core.resources.R.drawable.arrow_to_right),
					contentDescription = "",
					tint = MaterialTheme.colors.primary
				)
			}
		}
	}
}




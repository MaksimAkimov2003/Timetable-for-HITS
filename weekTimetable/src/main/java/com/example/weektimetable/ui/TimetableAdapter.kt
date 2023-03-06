package com.example.weektimetable.ui

import android.graphics.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MyDay (
	val name: String,
	val schedule: List<MySchedule>
)
data class MySchedule(
	val name: String,
	val time: String
)

class TimetableAdapter(private val items: List<MyDay>): ScrollTableAdapter() {

	private val textPaint = Paint().asFrameworkPaint().apply {
		isAntiAlias = true
		textSize = 48.sp.value
		color = Color.Black.toArgb()
	}
	private val rectPaint = Paint().asFrameworkPaint().apply {
		isAntiAlias = true
		color = Color.Green.toArgb()
	}

	private val offset = Rect()
	private val bounds = Rect()

	override fun drawRowMark(y: Int): CanvasBlock.() -> Unit = {
//		val start = items[0].schedule[y].time
		drawText(items[0].schedule[y].time, 0f, 0f, textPaint)
		gravity(CanvasBlock.Gravity.Center)
	}

	override fun drawColumnMark(x: Int): CanvasBlock.() -> Unit = {
		drawText(items[x].name, 0f, 0f, textPaint)
		gravity(CanvasBlock.Gravity.Center)
	}

	override fun drawItem(x: Int, y: Int): CanvasBlock.() -> Unit = {
		textPaint.getTextBounds(items[x].schedule[y].name, 0, items[x].schedule[y].name.length, bounds)
		textPaint.getTextBounds("1", 0, "1".length, offset)
		drawRect(0f, 0f, 512.dp.value + 32.dp.value, 512.dp.value, rectPaint)
		drawText(items[x].schedule[y].name, 0f, 0f, textPaint)
	}

	override fun rowCount(): Int {
		return items.size
	}

	override fun columnCount(): Int {
		return items[0].schedule.size
	}
}

package com.example.weektimetable.ui.scrolltable

import android.graphics.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weektimetable.domain.entity.DayEntity
import com.example.weektimetable.domain.entity.WeekEntity
import kotlin.math.round

private data class TimeSlot(val start: String, val end: String)

class TimetableAdapter(private val items: List<DayEntity>): ScrollTableAdapter() {

	private val timeslots = listOf(
		TimeSlot("8:45", "10:20"),
		TimeSlot("10:35", "12:10"),
		TimeSlot("12:25", "14:00"),
		TimeSlot("14:45", "16:20"),
		TimeSlot("16:35", "18:10"),
		TimeSlot("18:25", "20:00"),
		TimeSlot("20:15", "21:50")
	)

	private val markTextPaint = Paint().asFrameworkPaint().apply {
		isAntiAlias = true
		textSize = 48.sp.value
		color = Color.Black.toArgb()
	}
	private val itemTextPaint = Paint().asFrameworkPaint().apply {
		isAntiAlias = true
		textSize = 36.sp.value
		color = Color.Black.toArgb()
	}
	private val rectPaint = Paint().asFrameworkPaint().apply {
		isAntiAlias = true
		color = Color(4288383728).toArgb()
	}

	private val offset = Rect()

	override fun drawRowMark(y: Int): CanvasBlock.() -> Unit = {
		markTextPaint.getTextBounds("1", 0, "1".length, offset)
		drawText(timeslots[y].start, 0f, -2f * offset.height(), markTextPaint)
		drawText(timeslots[y].end, 0f, 2f * offset.height(), markTextPaint)
		gravity(CanvasBlock.Gravity.Center)
	}

	override fun drawColumnMark(x: Int): CanvasBlock.() -> Unit = {
		markTextPaint.getTextBounds("1", 0, "1".length, offset)
		drawText(items[x].weekDay, 0f, -1.5f * offset.height(), markTextPaint)
		drawText(items[x].day, 0f, 1.5f * offset.height(), markTextPaint)
		gravity(CanvasBlock.Gravity.Center)
	}

	override fun drawItem(x: Int, y: Int): CanvasBlock.() -> Unit = {
		itemTextPaint.getTextBounds("1", 0, "1".length, offset)
		items[x].timeSlots.forEach { slot ->
			var text = mutableListOf("")
			if(slot.slotNumber == y + 1) {
				slot.pairs?.forEach { pair ->
					var str = ""
					str += pair.discipline + "\n"
					str += pair.auditory + "\n"
					str += pair.professor + "\n"
					str += pair.lessonType + "\n"
					pair.groups.forEach { group ->
						str += group.number + "\n"
					}
					text.add(str)
				}
				drawTextByWidthInRect(text, 0f, 0f, itemTextPaint, rectPaint, 256.dp.value + 128.dp.value)
			}
		}
//		if(text.isNotEmpty()) drawRectByHeight(0f,256.dp.value + 128.dp.value, rectPaint)
//		drawTextByWidth(text, offset.width().toFloat(), offset.height().toFloat(), itemTextPaint)
	}

	override fun rowCount(): Int {
		return items.size
	}

	override fun columnCount(): Int {
		return timeslots.size
	}
}

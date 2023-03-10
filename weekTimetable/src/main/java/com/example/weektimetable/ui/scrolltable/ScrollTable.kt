package com.example.weektimetable.ui.scrolltable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned

abstract class ScrollTableAdapter {

	abstract fun drawRowMark(y: Int): CanvasBlock.() -> Unit
	abstract fun drawColumnMark(x: Int): CanvasBlock.() -> Unit
	abstract fun drawItem(x: Int, y: Int): CanvasBlock.() -> Unit

	abstract fun rowCount(): Int
	abstract fun columnCount(): Int

}

@Composable
fun ScrollTable(adapter: ScrollTableAdapter) {

	val rowMarks = mutableListOf<CanvasBlock>()
	val columnMarks = mutableListOf<CanvasBlock>()
	val table = mutableListOf(mutableListOf<CanvasBlock>())

	for(i in 0 .. adapter.columnCount()) {
		if(i > 1) table.add(mutableListOf())
		for(j in 0 .. adapter.rowCount()) {
			val block = CanvasBlock()
			val drawBlock: CanvasBlock.() -> Unit
//			try {
				when {
					i == 0 && j == 0 	-> {  }
					i == 0 				-> {
						drawBlock = adapter.drawRowMark(j - 1)
						block.drawBlock()
						rowMarks.add(block)
					}
					j == 0 				-> {
						drawBlock = adapter.drawColumnMark(i - 1)
						block.drawBlock()
						columnMarks.add(block)
					}
					else   				-> {
						drawBlock = adapter.drawItem(i - 1, j - 1)
						block.drawBlock()
						table[i-1].add(block)
					}
				}
//			} catch(_: Exception) {
//				println("error")
//			}
		}
	}

	DrawTable(adapter, rowMarks, columnMarks, table)
}

@Composable
private fun DrawTable(
    adapter: ScrollTableAdapter,
    rowMarks: List<CanvasBlock>,
    columnMarks: List<CanvasBlock>,
    table: List<List<CanvasBlock>>) {

	val state = rememberSaveable(saver = ScrollTableState.Saver) {
        ScrollTableState.getState(
            rowMarks,
            columnMarks,
            table
        )
    }
	val backgroundPaint = Paint().asFrameworkPaint().apply {
		isAntiAlias = true
		color = Color.White.toArgb()
	}
	Canvas(modifier = Modifier
		.fillMaxSize()
		.background(MaterialTheme.colors.background)
		.scrollable(state.horizontalScrollState, Orientation.Horizontal)
		.scrollable(state.verticalScrollState, Orientation.Vertical)
		.onGloballyPositioned { state.setViewSize(it.size.width.toFloat(), it.size.height.toFloat()) }) {

		state.measurements.apply {
			for(i in 1 .. adapter.columnCount()) {
				for(j in 1 .. adapter.rowCount()) {
					translate(column[i-1], 0f) {
						drawLine(Color.Black, Offset(-strokeWidth / 2, 0f), Offset(-strokeWidth / 2, row.last() ), strokeWidth = strokeWidth)
					}
					translate(0f, row[j-1]) {
						drawLine(Color.Black, Offset(0f, -strokeWidth / 2), Offset(column.last(), -strokeWidth / 2), strokeWidth = strokeWidth)
					}
					translate(column[i-1] + offsetX, row[j-1] + offsetY) {
						table[i-1][j-1].content.forEach{ it() }
					}
				}
			}
			for(i in 1 .. adapter.columnCount()) {
				translate(column[i-1], 0f) {
					drawContext.canvas.nativeCanvas.drawRect(0f, 0f, column[i] - column[i - 1] - strokeWidth, columnMarkLine, backgroundPaint)
					translate(offsetX, offsetY) { columnMarks[i - 1].content.forEach { it() }}
				}
			}
			for(j in 1 .. adapter.rowCount()) {
				translate(0f, row[j-1]) {
					drawContext.canvas.nativeCanvas.drawRect(0f, 0f, rowMarkLine, row[j] - row[j - 1] - strokeWidth, backgroundPaint)
					translate(offsetX, offsetY) { rowMarks[j - 1].content.forEach { it() } }
				}
			}

			drawContext.canvas.nativeCanvas.drawRect(0f, 0f, rowMarkLine, columnMarkLine, backgroundPaint)
			drawLine(Color.Black, Offset(0f, columnMarkLine - strokeWidth/2), Offset(column.last(), columnMarkLine - strokeWidth/2), strokeWidth = strokeWidth)
			drawLine(Color.Black, Offset(rowMarkLine - strokeWidth/2, 0f), Offset(rowMarkLine - strokeWidth/2, row.last()), strokeWidth = strokeWidth)
		}
	}
}

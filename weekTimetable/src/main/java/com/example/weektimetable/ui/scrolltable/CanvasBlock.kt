package com.example.weektimetable.ui.scrolltable

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import java.lang.Float.max

class CanvasBlock {

	var width: Float = 0f
	var height: Float = 0f

	private var nextBlock = 0f

	private var gravity: Gravity = Gravity.LeftTop
	fun gravity(gravity: Gravity) { this.gravity = gravity }

	var content: MutableList<DrawScope.() -> Unit> = mutableListOf()

	fun drawText(text: String, x: Float, y: Float, paint: Paint) {
		val offset = Rect()
		val bounds = Rect()
		paint.getTextBounds("1", 0, "1".length, offset)
		paint.getTextBounds(text, 0, text.length, bounds)
		content.add {
			handleGravity(x + bounds.width(),y + offset.height()) {
				drawContext.canvas.nativeCanvas.drawText(text, x, y + offset.height(), paint)
			}
		}
		height = max(height, y + offset.height())
		width = max(width, x + bounds.width())
	}

	fun drawTextByWidth(text: String, x: Float, y: Float, paint: Paint) {
		val bounds = Rect()
		val offset = Rect()
		paint.getTextBounds("111", 0, "111".length, offset)
		val lines = mutableListOf<String>()
		var line = ""
		for(i in text.indices) {
			line += text[i]
			paint.getTextBounds(line, 0, line.length, bounds)
			if(bounds.width() + offset.width() > width) {
				lines.add(line)
				line = ""
			}
			else if(i < text.length - 1 && text.substring(i, i+1) == "\n") {
				lines.add(line)
				line = ""
			}
		}
		if(line.isNotEmpty()) lines.add(line)
		content.add {
			handleGravity(x, y) {
				lines.forEachIndexed { i, it ->
					drawContext.canvas.nativeCanvas.drawText(it, x, y + i * 2 * offset.height() + offset.height(), paint)
				}
			}
		}
		height = max(height, y + lines.size * 2 * offset.height() + offset.height())
	}

	fun drawTextByWidthInRect(text: List<String>, x: Float, y: Float, textPaint: Paint, rectPaint: Paint, width: Float) {
		this.width = width
		val bounds = Rect()
		val offset = Rect()
		textPaint.getTextBounds("1111", 0, "1111".length, offset)
		val lines = mutableListOf<MutableList<String>>()
		var line = ""
		for(i in text) {
			val newBlock = mutableListOf<String>()
			for (j in i.indices) {
				line += i[j]
				textPaint.getTextBounds(line, 0, line.length, bounds)
				if (bounds.width() + offset.width() > width) {
					if (line == "\n") continue
					newBlock.add(line)
					line = ""
				} else if (j < i.length - 1 && i.substring(j, j + 1) == "\n") {
					if (line == "\n") continue
					newBlock.add(line)
					line = ""
				}
			}
			if (newBlock.isNotEmpty() && line != "\n") {
				newBlock.add(line)
				lines.add(newBlock)
			}
		}
		var newHeight = y + lines.size * 16.dp.value
		lines.forEach {
			newHeight += it.size * 2 * offset.height() + offset.height()
		}
		content.add {
			handleGravity(x, y) {
				var start = 0f
				lines.forEachIndexed {j, it ->
					start += if(j!=0) j * 8.dp.value + lines[j-1].size * 2f * offset.height() + offset.height() else 0f
					drawContext.canvas.nativeCanvas.drawRoundRect(
						0f,
						start,
						width,
						start + j * 8.dp.value + y + it.size * 2 * offset.height() + offset.height(),
						8.dp.value,
						8.dp.value ,
						rectPaint
					)
					it.forEachIndexed { i, block ->
						drawContext.canvas.nativeCanvas.drawText(
							block,
							x + offset.width() / 4,
							start + offset.height() + y + i * 2 * offset.height() + offset.height(),
							textPaint
						)
					}
				}
			}
		}
		height = max(height, newHeight)
	}

	fun drawRect(left: Float, top: Float, right: Float, bottom: Float, paint: Paint) {
		content.add {
			handleGravity(right, bottom) {
				drawContext.canvas.nativeCanvas.drawRect(left, top, right, bottom, paint)
			}
		}
		width = max(width, right)
		height = max(height, bottom)
	}

	fun drawRectByHeight(top: Float, bottom: Float, paint: Paint) {
		content.add {
			handleGravity(bottom, height) {
				drawContext.canvas.nativeCanvas.drawRoundRect(top, 0f, bottom, height, 8.dp.value, 8.dp.value , paint)
			}
		}
		width = max(width, bottom)
	}

	fun drawRectByWidth(left: Float, right: Float, paint: Paint) {
		content.add {
			handleGravity(width, right) {
				drawContext.canvas.nativeCanvas.drawRect(0f, left, width, right, paint)
			}
		}
		height = max(height, right)
	}

	private fun DrawScope.handleGravity(width: Float, height: Float, content: DrawScope.() -> Unit) {
		if (gravity == Gravity.Center) {
			translate(this@CanvasBlock.width / 2 - width / 2, this@CanvasBlock.height / 2 - height / 2) {
				content()
			}
		}
		else {
			content()
		}
	}

	enum class Size {
		MAX_WIDTH,
		MAX_HEIGHT,
	}

	enum class Gravity {
		Center,
//		Left,
//		Top,
//		Right,
//		Bottom,
		LeftTop,
//		TopRight,
//		RightBottom,
//		BottomLeft
	}
}

fun DrawScope.translate(x: Float, y: Float, content: DrawScope.() -> Unit) {
	drawContext.transform.translate(x, y)
	content()
	drawContext.transform.translate(-x, -y)
}



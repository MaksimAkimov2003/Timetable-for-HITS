package com.example.weektimetable.ui

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawContext
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import java.lang.Float.max

class CanvasBlock {

	var width: Float = 0f
	var height: Float = 0f

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

	fun drawRect(left: Float, top: Float, right: Float, bottom: Float, paint: Paint) {
		content.add {
			handleGravity(right, bottom) {
				drawContext.canvas.nativeCanvas.drawRect(left, top, right, bottom, paint)
			}
		}
		width = max(width, right)
		height = max(height, bottom)
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



package com.example.mockapiserver.assets

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.InputStreamReader
import java.net.HttpURLConnection

internal fun getFake(
	context: Context, uri: Uri,
	response: Response.Builder
): Response.Builder =
	when (uri.path) {
		"insert/your/request/here" -> {
			response.createResponse(
				description = context.readFileFromAssets(TestAssetReader.testAsset),
				body = context.readFileFromAssets(TestAssetReader.testAsset)
			)
		}

		"/api/teachers"            -> {
			response.createResponse(
				description = context.readFileFromAssets(TeachersAssetReader.teachers),
				body = context.readFileFromAssets(TeachersAssetReader.teachers)
			)
		}

		"/api/faculties"           -> {
			response.createResponse(
				description = context.readFileFromAssets(FacultiesAssetReader.faculties),
				body = context.readFileFromAssets(FacultiesAssetReader.faculties)
			)
		}

		"/api/disciplines/1"       -> {
			response.createResponse(
				description = context.readFileFromAssets(DirectionsAssetReader.faculty1),
				body = context.readFileFromAssets(DirectionsAssetReader.faculty1)
			)
		}

		"/api/disciplines/2"       -> {
			response.createResponse(
				description = context.readFileFromAssets(DirectionsAssetReader.faculty2),
				body = context.readFileFromAssets(DirectionsAssetReader.faculty2)
			)
		}

		"/api/disciplines/3"       -> {
			response.createResponse(
				description = context.readFileFromAssets(DirectionsAssetReader.faculty3),
				body = context.readFileFromAssets(DirectionsAssetReader.faculty3)
			)
		}

		"/api/disciplines/4"       -> {
			response.createResponse(
				description = context.readFileFromAssets(DirectionsAssetReader.faculty4),
				body = context.readFileFromAssets(DirectionsAssetReader.faculty4)
			)
		}

		"/api/disciplines/5"       -> {
			response.createResponse(
				description = context.readFileFromAssets(DirectionsAssetReader.faculty5),
				body = context.readFileFromAssets(DirectionsAssetReader.faculty5)
			)
		}

		else                       -> {
			error404(response)
		}
	}

internal fun Response.Builder.createResponse(
	code: Int = HttpURLConnection.HTTP_OK,
	description: String,
	body: String? = null
) =
	this.code(code)
		.message(description)
		.apply {
			body?.let {
				body(it.toResponseBody("application/json".toMediaTypeOrNull()))
			}
		}

internal fun error404(response: Response.Builder) =
	response.createResponse(
		code = HttpURLConnection.HTTP_NOT_FOUND,
		description = "Mistake in URL",
		body = """{ "error": "Mistake in URL" }"""
	)

internal fun Context.readFileFromAssets(path: String): String =
	assets.open(path)
		.reader()
		.use(InputStreamReader::readText)
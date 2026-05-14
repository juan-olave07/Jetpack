package com.example.taller_jetpack.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton
import com.example.taller_jetpack.BuildConfig

@Singleton
class IARepository @Inject constructor(
    private val okHttpClient: OkHttpClient
) {
    fun generarRecetasStream(ingredientes: List<String>): Flow<String> = flow {
        val prompt = """
            Tengo estos ingredientes: ${ingredientes.joinToString(", ")}.
            Sugiere 3 recetas posibles en formato JSON con campos:
            nombre, tiempo_minutos, dificultad, pasos (lista), calorias.
            Solo JSON, sin texto adicional. El JSON debe ser un array de objetos.
        """.trimIndent()

        val body = JSONObject().apply {
            put("model", "llama-3.3-70b-versatile")
            put("stream", true)
            put("messages", JSONArray().apply {
                put(JSONObject().apply {
                    put("role", "user")
                    put("content", prompt)
                })
            })
        }.toString().toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("https://api.groq.com/openai/v1/chat/completions")
            .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
            .post(body)
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw Exception("Error Groq: ${response.code} - ${response.body.string()}")
            }
            response.body.source().let { source ->
                while (!source.exhausted()) {
                    val line = source.readUtf8Line() ?: break
                    if (line.startsWith("data: ") && line != "data: [DONE]") {
                        runCatching {
                            val json = JSONObject(line.removePrefix("data: "))
                            json.getJSONArray("choices")
                                .getJSONObject(0)
                                .getJSONObject("delta")
                                .optString("content", "")
                        }.getOrNull()?.let { delta ->
                            if (delta.isNotEmpty()) emit(delta)
                        }
                    }
                }
            }
        }
    }
}

package com.example.taller_jetpack.utils

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class ImageAnalyzer(
    private val onIngredientesDetectados: (List<String>) -> Unit
) : ImageAnalysis.Analyzer {

    private val labeler = ImageLabeling.getClient(
        ImageLabelerOptions.Builder()
            .setConfidenceThreshold(0.75f)
            .build()
    )

    // Control para capturar solo un frame
    var capturar = false

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        if (!capturar) {
            imageProxy.close()
            return
        }
        capturar = false

        val mediaImage = imageProxy.image ?: run { imageProxy.close(); return }
        val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        labeler.process(inputImage)
            .addOnSuccessListener { labels ->
                val ingredientes = labels
                    .filter { it.confidence > 0.75f }
                    .map { it.text }
                onIngredientesDetectados(ingredientes)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}
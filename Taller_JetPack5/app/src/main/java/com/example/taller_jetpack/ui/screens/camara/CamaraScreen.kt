package com.example.taller_jetpack.ui.screens.camara

import android.Manifest
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.taller_jetpack.utils.ImageAnalyzer
import java.util.concurrent.Executors
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import android.content.pm.PackageManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CamaraScreen(
    onIngredientesDetectados: (List<String>) -> Unit,
    viewModel: CamaraViewModel
) {
    val context = LocalContext.current
    val analizando by viewModel.analizando.collectAsStateWithLifecycle() // <-- esto faltaba

    var permisoOtorgado by rememberSaveable {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { concedido ->
        permisoOtorgado = concedido
    }

    LaunchedEffect(Unit) {
        if (!permisoOtorgado) launcher.launch(Manifest.permission.CAMERA)
    }

    if (!permisoOtorgado) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Se necesita permiso de cámara")
                Spacer(Modifier.height(8.dp))
                Button(onClick = { launcher.launch(Manifest.permission.CAMERA) }) {
                    Text("Otorgar permiso")
                }
            }
        }
        return
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val executor = remember { Executors.newSingleThreadExecutor() }
    var analyzerRef by remember { mutableStateOf<ImageAnalyzer?>(null) }

    Box(Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                val previewView = PreviewView(ctx)

                val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()

                    val preview = Preview.Builder().build().also {
                        it.surfaceProvider = previewView.surfaceProvider
                    }

                    val imageAnalyzer = ImageAnalyzer { ingredientes ->
                        viewModel.setAnalizando(false)
                        onIngredientesDetectados(ingredientes)
                    }.also { analyzerRef = it }

                    val analysisUseCase = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also { it.setAnalyzer(executor, imageAnalyzer) }

                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        analysisUseCase
                    )
                }, ContextCompat.getMainExecutor(ctx))

                previewView
            },
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (analizando) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(8.dp))
                Text("Analizando...", color = MaterialTheme.colorScheme.onPrimary)
            } else {
                FloatingActionButton(
                    onClick = {
                        viewModel.setAnalizando(true)
                        analyzerRef?.capturar = true
                    }
                ) {
                    Icon(Icons.Default.Camera, "Capturar")
                }
            }
        }
    }
}
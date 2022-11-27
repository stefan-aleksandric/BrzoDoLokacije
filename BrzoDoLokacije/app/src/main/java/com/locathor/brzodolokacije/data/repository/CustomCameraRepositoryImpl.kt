package com.locathor.brzodolokacije.data.repository

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.locathor.brzodolokacije.domain.repository.CustomCameraRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomCameraRepositoryImpl @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val selector: CameraSelector,
    private val preview: Preview,
//    private val imageAnalysis: ImageAnalysis,
    private val imageCapture: ImageCapture
): CustomCameraRepository {
    override suspend fun captureAndSaveImage(context: Context) {
        //filename
        val name = SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.ENGLISH
        ).format(System.currentTimeMillis())

        //storage
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if(Build.VERSION.SDK_INT > 28){
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Camera-X-Clean-Arch-Images")
            }
        }

        val outputOptions = ImageCapture
            .OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    //here we can get saved image uri
                    Toast.makeText(
                        context,
                        "Saved image ${outputFileResults.savedUri!!}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        context,
                        "Error ${exception.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        )


    }

    override suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
    ) {
        preview.setSurfaceProvider(previewView.surfaceProvider)
        try {
          cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                selector,
                preview,
//                imageAnalysis,
                imageCapture
            )
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}
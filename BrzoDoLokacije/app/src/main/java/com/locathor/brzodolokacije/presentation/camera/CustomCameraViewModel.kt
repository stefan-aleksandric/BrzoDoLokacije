package com.locathor.brzodolokacije.presentation.camera

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locathor.brzodolokacije.domain.repository.CustomCameraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomCameraViewModel @Inject constructor(
    private val repo: CustomCameraRepository
): ViewModel() {

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ){
        viewModelScope.launch {
            repo.showCameraPreview(
                previewView,
                lifecycleOwner
            )
        }
    }

    fun captureAndSave(context: Context){
        viewModelScope.launch {
            repo.captureAndSaveImage(context)
        }
    }
}
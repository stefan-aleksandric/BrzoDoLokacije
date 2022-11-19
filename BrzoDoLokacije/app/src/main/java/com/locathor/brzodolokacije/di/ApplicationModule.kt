package com.locathor.brzodolokacije.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.camera.core.*
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.locathor.brzodolokacije.data.repository.CustomCameraRepositoryImpl
import com.locathor.brzodolokacije.data.local.BrzoDoLokacijeDatabase
import com.locathor.brzodolokacije.data.remote.PostApi
import com.locathor.brzodolokacije.data.remote.UserApi
import com.locathor.brzodolokacije.data.services.AppSharedPreferences
import com.locathor.brzodolokacije.data.remote.interceptors.AuthInterceptorImpl
import com.locathor.brzodolokacije.data.services.SessionManager
import com.locathor.brzodolokacije.domain.repository.AuthRepository
import com.locathor.brzodolokacije.domain.repository.CustomCameraRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    /*  USER API DEPENDENCY CHAIN */
    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(UserApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    /*  POST API DEPENDENCY CHAIN */
    @Provides
    @Singleton
    fun providePostApi(retrofit: Retrofit): PostApi =
       retrofit
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(PostApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptorImpl: AuthInterceptorImpl): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptorImpl)
            .build()
    @Singleton
    @Provides
    fun provideAuthInterceptorImpl(
        sessionManager: SessionManager
    ): AuthInterceptorImpl = AuthInterceptorImpl(sessionManager)



    /* SESSION MANAGER DEPENDENCY CHAIN */
    @Singleton
    @Provides
    fun provideSessionManager(
        appSharedPreferences: AppSharedPreferences,
        authRepository: AuthRepository
    ): SessionManager = SessionManager(appSharedPreferences, authRepository)

    @Singleton
    @Provides
    fun provideAppSharedPreferences(
        sharedPreferences: SharedPreferences
    ): AppSharedPreferences = AppSharedPreferences(sharedPreferences)

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences =
        context.getSharedPreferences(AppSharedPreferences.SHARED_PREFS, Context.MODE_PRIVATE)


    /* DATABASE */
    @Provides
    @Singleton
    fun provideBrzoDoLokacijeDatabase(app: Application): BrzoDoLokacijeDatabase {
        return Room.databaseBuilder(
            app,
            BrzoDoLokacijeDatabase::class.java,
            "brzodolokacije.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    /* FUSED LOCATION PROVIDER */
    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(
        app: Application
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }


    //CAMERA
    @Provides
    @Singleton
    fun provideCameraSelector(): CameraSelector {
        return CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraProvider(application: Application)
            : ProcessCameraProvider {
        return ProcessCameraProvider.getInstance(application).get()

    }

    @Provides
    @Singleton
    fun provideCameraPreview(): Preview {
        return Preview.Builder().build()
    }

    @Provides
    @Singleton
    fun provideImageCapture(): ImageCapture {
        return ImageCapture.Builder()
            .setFlashMode(FLASH_MODE_ON)
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideImageAnalysis(): ImageAnalysis {
//        return ImageAnalysis.Builder()
//            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
//            .build()
//    }

    @Provides
    @Singleton
    fun provideCustomCameraRepository(
        cameraProvider: ProcessCameraProvider,
        selector: CameraSelector,
        imageCapture: ImageCapture,
//        imageAnalysis: ImageAnalysis,
        preview: Preview
    ): CustomCameraRepository {
        return CustomCameraRepositoryImpl(
            cameraProvider,
            selector,
            preview,
//            imageAnalysis,
            imageCapture
        )
    }
}
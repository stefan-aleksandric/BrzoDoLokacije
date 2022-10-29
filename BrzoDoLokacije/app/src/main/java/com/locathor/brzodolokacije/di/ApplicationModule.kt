package com.locathor.brzodolokacije.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.locathor.brzodolokacije.data.local.BrzoDoLokacijeDatabase
import com.locathor.brzodolokacije.data.remote.PostApi
import com.locathor.brzodolokacije.data.remote.UserApi
import com.locathor.brzodolokacije.data.repository.AuthRepositoryImpl
import com.locathor.brzodolokacije.domain.repository.AuthRepository
import com.locathor.brzodolokacije.util.AppSharedPreferences
import com.locathor.brzodolokacije.util.AuthInterceptor
import com.locathor.brzodolokacije.util.SessionManager
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

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(UserApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

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
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()


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

    @Singleton
    @Provides
    fun provideAuthInterceptorImpl(
        sessionManager: SessionManager
    ): AuthInterceptor = AuthInterceptor(sessionManager = sessionManager)


    @Singleton
    @Provides
    fun provideSessionManager(
        appSharedPreferences: AppSharedPreferences,
        authRepository: AuthRepository
    ): SessionManager = SessionManager(
        pref = appSharedPreferences,
        authRepository = authRepository
    )

    @Singleton
    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

    @Singleton
    @Provides
    fun provideAppSharedPreferences(
        sharedPreferences: SharedPreferences
    ) = AppSharedPreferences(sharedPreferences)


    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences =
        context.getSharedPreferences(AppSharedPreferences.SHARED_PREFS, Context.MODE_PRIVATE)
}
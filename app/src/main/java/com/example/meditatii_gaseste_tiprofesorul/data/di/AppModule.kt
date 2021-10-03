package com.example.meditatii_gaseste_tiprofesorul.data.di

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.example.meditatii_gaseste_tiprofesorul.common.Constants
import com.example.meditatii_gaseste_tiprofesorul.data.remote.CityApi
import com.example.meditatii_gaseste_tiprofesorul.data.repository.cityRepository.CityRepository
import com.example.meditatii_gaseste_tiprofesorul.data.repository.cityRepository.impl.CityRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideSvgLoader(@ApplicationContext context: Context): ImageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            add(SvgDecoder(context))
        }
        .build()

    @Provides
    @Singleton
    fun provideCityApi(moshi: Moshi): CityApi = Retrofit.Builder()
        .baseUrl(Constants.CITY_API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(CityApi::class.java)

    @Provides
    @Singleton
    fun provideCityRepository(api: CityApi): CityRepository = CityRepositoryImpl(api)
}
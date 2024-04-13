package com.alix01z.cryptoyar.di.modules

import com.alix01z.cryptoyar.network.RequestAPI
import com.alix01z.cryptoyar.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltNetworkModule {
    val BASE_URL = "https://api.coinmarketcap.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                // API-Key is not required here , so we dont need to add @Header here!
//                    .newBuilder()
//                    .addHeader("Authorization", "Bearer YourTokenHere")
//                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideRequestApi(retrofit: Retrofit): RequestAPI =
        retrofit.create(RequestAPI::class.java)


    @Provides
    @Singleton
    fun provideAppRepository(requestAPI: RequestAPI): AppRepository{
        return AppRepository(requestAPI)
    }
}
package com.example.simpleweather.di.modules

//@Module
class WeatherModule {



//    @Provides
//    internal fun getUnsafeOkHttpClient(connectivityInterceptor: ConnectivityInterceptor) : UnsafeHttpOkClientImpl {
//        return UnsafeHttpOkClientImpl(connectivityInterceptor)
//    }
//
//    @Provides
//    internal fun provideOpenWeatherApi(connectivityInterceptor: ConnectivityInterceptor) : OpenWeatherApiService {
//        return Retrofit.Builder()
//            .client(UnsafeHttpOkClientImpl(connectivityInterceptor).getUnsafeOkHttpClient())
//            .baseUrl(BASE_URL)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(OpenWeatherApiService::class.java)
//    }
}
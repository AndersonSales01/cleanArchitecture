package com.anderson.cleanarchitecture.data.api.config

import com.anderson.cleanarchitecture.constants.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    var retrofit: Retrofit? = null

    fun getInstance() : Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        if (retrofit == null) {

            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
//                    //Responsavel por criar objetos RX
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //Aplicando coroutine adapter
              //  .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.client(httpClient.build())
                .build()
        }

        return retrofit!!

    }
}
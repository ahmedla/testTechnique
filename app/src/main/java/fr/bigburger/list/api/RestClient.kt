package fr.bigburger.list.api

import kotlin.jvm.java
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import fr.bigburger.BuildConfig
import retrofit2.converter.moshi.MoshiConverterFactory

class RestClient {

    companion object {

        fun create(): ApiServiceInterface {

            val retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}
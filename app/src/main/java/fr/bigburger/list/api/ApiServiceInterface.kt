package fr.bigburger.list.api

import fr.bigburger.list.entity.Burger
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("/catalog")
    fun getBurgersAsync(): Deferred<Response<List<Burger>>>
}
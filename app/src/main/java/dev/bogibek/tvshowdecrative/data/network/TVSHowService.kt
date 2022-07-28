package dev.bogibek.tvshowdecrative.data.network

import dev.bogibek.tvshowdecrative.model.TVShowDetails
import dev.bogibek.tvshowdecrative.model.TVShowPopular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVSHowService {

    @GET("api/most-popular")
    suspend fun apiTVShowPopular(@Query("page") page: Int): Response<TVShowPopular>

    @GET("api/show-details")
    suspend fun apiTVShowDetails(@Query("q") q: Int): Response<TVShowDetails>

}
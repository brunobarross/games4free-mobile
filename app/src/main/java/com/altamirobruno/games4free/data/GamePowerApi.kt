package com.altamirobruno.games4free.data

import com.altamirobruno.games4free.model.Giveaway
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GamePowerApi {
  @GET("api/giveaways/")
  suspend fun getGiveaways(
    @Query("type") type: String? = null,
    @Query("platform") platform: String? = null,
  ): Response<List<Giveaway>>

  suspend fun getGiveaway(
    @Query("id") type: Int? = null,

    ): Response<Giveaway>
}

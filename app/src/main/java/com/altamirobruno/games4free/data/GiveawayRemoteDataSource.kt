package com.altamirobruno.games4free.data

import com.altamirobruno.games4free.model.Giveaway
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GiveawayRemoteDataSource {
  suspend fun getGiveaways(type: String? = null): Result<List<Giveaway>> {
    return withContext(Dispatchers.IO) {
      try {
        val response =
          HTTPClient.retrofit().create(GamePowerApi::class.java).getGiveaways(type)
        if (response.isSuccessful) {
          val giveaways = response.body() ?: emptyList<Giveaway>()
          Result.success(giveaways)
        } else {
          val error = response.errorBody()?.string() ?: "Erro desconhecido"
          Result.failure(Exception(error))
        }
      } catch (e: Exception) {
        Result.failure(e)
      }

    }

  }
}

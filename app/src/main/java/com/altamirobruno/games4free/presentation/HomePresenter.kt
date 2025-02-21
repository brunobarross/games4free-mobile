package com.altamirobruno.games4free.presentation

import android.util.Log
import com.altamirobruno.games4free.data.GiveawayRemoteDataSource
import com.altamirobruno.games4free.model.Giveaway
import com.altamirobruno.games4free.view.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter(
  private val view: HomeFragment,
  private val dataSource: GiveawayRemoteDataSource = GiveawayRemoteDataSource()
) {
  val data = mutableListOf<Giveaway>()
  fun loadingGiveAways() {
    CoroutineScope(Dispatchers.IO).launch {
      try {
        val data = dataSource.getGiveaways("game")
        Log.d("jogaços", data.toString())
      } catch (e: Exception) {
        withContext(Dispatchers.Main) {
          e.message?.let { Log.d("error", it) }
        }
      }
    }
  }

}

package com.altamirobruno.games4free.presentation

import android.util.Log
import com.altamirobruno.games4free.data.GiveawayRemoteDataSource
import com.altamirobruno.games4free.model.Category
import com.altamirobruno.games4free.view.GiveawayFragment
import com.altamirobruno.games4free.view.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GiveawayPresenter(
  private val view: GiveawayFragment,
  private val dataSource: GiveawayRemoteDataSource = GiveawayRemoteDataSource()
) {

  fun loadingGiveAway(id: Int) {
    view.showProgress()
    CoroutineScope(Dispatchers.IO).launch {
      try {
        val game = dataSource.getGiveaway(id).getOrNull()

        withContext(Dispatchers.Main) {
          if (game !== null) {
            view.showGiveaway(game)
          }
        }


      } catch (e: Exception) {
        withContext(Dispatchers.Main) {
          e.message?.let { Log.d("error", it) }
        }
      } finally {
        withContext(Dispatchers.Main) {
          view.hideProgress()
        }

      }
    }
  }

}

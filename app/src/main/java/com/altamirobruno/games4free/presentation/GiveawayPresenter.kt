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
  val categories = mutableListOf<Category>()
  fun loadingGiveAway() {
    view.showProgress()
    CoroutineScope(Dispatchers.IO).launch {
      try {
        val game = dataSource.getGiveaway(1).getOrNull()

        withContext(Dispatchers.Main) {
          view.showGiveaway(categories)
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

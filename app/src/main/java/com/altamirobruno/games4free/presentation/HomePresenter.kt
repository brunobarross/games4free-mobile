package com.altamirobruno.games4free.presentation

import android.util.Log
import com.altamirobruno.games4free.data.GiveawayRemoteDataSource
import com.altamirobruno.games4free.model.Category
import com.altamirobruno.games4free.view.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: GiveawayRemoteDataSource = GiveawayRemoteDataSource()
) {
    val categories = mutableListOf<Category>()
    fun loadingGiveAways() {
        view.showProgress()
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val games = dataSource.getGiveaways("game", ).getOrNull()
                val loots = dataSource.getGiveaways("loot").getOrNull()
                withContext(Dispatchers.Main) {
                    if (!games.isNullOrEmpty()) {
                        categories.add(
                            Category(
                                id = 1,
                                name = "Games",
                                giveaways = games
                            )
                        )
                    }
                    if (!loots.isNullOrEmpty()) {
                        categories.add(
                            Category(
                                id = 2,
                                name = "DLC's",
                                giveaways = loots
                            )
                        )
                    }
                    view.showGiveaways(categories)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                  e.message?.let { view.showErrorToast(it.toString()) }
                }
            } finally {
                withContext(Dispatchers.Main) {
                    view.hideProgress()
                }

            }
        }
    }

}

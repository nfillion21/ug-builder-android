package pgm.poolp.ugbuilder.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroRepository @Inject constructor(private val heroDao: HeroDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allPlayers: Flow<List<Hero>> = heroDao.getPlayers()
    val allHeroes: Flow<List<Hero>> = heroDao.getHeroes()
    val allVillains: Flow<List<Hero>> = heroDao.getVillains()
    val allAllies: Flow<List<Hero>> = heroDao.getAllies()

    val allPlayersExceptVillains: Flow<List<Hero>> = heroDao.getAllPlayersExceptVillains()
    val allPlayersExceptVillainsOrderBySide: Flow<List<Hero>> = heroDao.getAllPlayersExceptVillainsOrderBySide()
    val allPlayersExceptVillainsOrderByName: Flow<List<Hero>> = heroDao.getAllPlayersExceptVillainsOrderByName()
    val allPlayersOrderByName: Flow<List<Hero>> = heroDao.getAllPlayersOrderByName()
    val allPlayersOrderBySide: Flow<List<Hero>> = heroDao.getAllPlayersOrderBySide()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    /*
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(champion: Champion) {
        championDao.insert(champion)
    }
    */
}

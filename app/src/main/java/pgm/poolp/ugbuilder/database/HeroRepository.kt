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

    fun getHero(heroId:String) = heroDao.getHero(heroId)
}

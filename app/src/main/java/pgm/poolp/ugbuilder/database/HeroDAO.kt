package pgm.poolp.ugbuilder.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("select * from hero order by side")
    fun getPlayers(): Flow<List<Hero>>

    @Query("select * from hero where side='Hero'")
    fun getHeroes(): Flow<List<Hero>>

    @Query("select * from hero where side='Villain'")
    fun getVillains(): Flow<List<Hero>>

    @Query("select * from hero where side='Ally'")
    fun getAllies(): Flow<List<Hero>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hero: Hero)

    @Query("delete from hero")
    suspend fun deleteAll()

    @Query("select * from hero where heroId = :heroId")
    fun getHero(heroId: String): Flow<Hero>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(heroes: List<Hero>)
}

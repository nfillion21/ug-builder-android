package pgm.poolp.ugbuilder.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface PlayerCartCrossRefDao {

    /*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(championSkillCrossRef: List<PlayerCartCrossRef>)
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(championSkillCrossRef: PlayerCartCrossRef)
}

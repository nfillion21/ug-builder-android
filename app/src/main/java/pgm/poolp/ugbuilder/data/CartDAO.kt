package pgm.poolp.ugbuilder.data

import androidx.room.*

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(carts: List<Cart>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayerCart(playerCartCrossRef: PlayerCartCrossRef)
}

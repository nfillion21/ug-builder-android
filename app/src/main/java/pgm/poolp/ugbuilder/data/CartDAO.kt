package pgm.poolp.ugbuilder.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(carts: List<Cart>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayerCart(playerCartCrossRef: PlayerCartCrossRef)

    @Transaction
    @Query("select * from cart where cartId = :cartId")
    fun getCartWithPlayers(cartId: String): Flow<CartWithPlayers>

    @Delete
    suspend fun deletePlayerCart(playerCartCrossRef: PlayerCartCrossRef)
}

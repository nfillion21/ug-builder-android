package pgm.poolp.ugbuilder.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(private val cartDAO: CartDAO) {
    suspend fun insertPlayerCart(playerCartCrossRef:PlayerCartCrossRef) {
        cartDAO.insertPlayerCart(playerCartCrossRef)
    }

    suspend fun deletePlayerCart(playerCartCrossRef:PlayerCartCrossRef) {
        cartDAO.deletePlayerCart(playerCartCrossRef)
    }

    fun getCartWithPlayers(cartId: String) = cartDAO.getCartWithPlayers(cartId)
}

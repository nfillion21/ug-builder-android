package pgm.poolp.ugbuilder.data

import androidx.room.*

data class CartWithPlayers(
    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "cartId",
        entityColumn = "heroId",
        associateBy = Junction(PlayerCartCrossRef::class)
    )
    val players: List<Hero>
)

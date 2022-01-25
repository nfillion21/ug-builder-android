package pgm.poolp.ugbuilder.data

import androidx.room.*

@Entity(primaryKeys = ["heroId", "cartId"])
data class PlayerCartCrossRef(
    val heroId: String,
    val cartId: String,
)
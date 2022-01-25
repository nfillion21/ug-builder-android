package pgm.poolp.ugbuilder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @PrimaryKey (autoGenerate = false) val cartId: String,
    val name: String
)
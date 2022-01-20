package pgm.poolp.ugbuilder.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hero(
    @PrimaryKey (autoGenerate = false) val heroId: String,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val side: String
)
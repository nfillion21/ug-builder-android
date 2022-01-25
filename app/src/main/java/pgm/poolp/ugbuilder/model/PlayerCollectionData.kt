package pgm.poolp.ugbuilder.model

import androidx.compose.runtime.Immutable
import pgm.poolp.ugbuilder.data.Hero

@Immutable
data class PlayerCollectionData(
    val id: Long,
    val name: String,
    val players: List<Hero>,
)

/**
 * A fake repo
 */
object PlayerRepo {
    //fun getPlayers(): List<PlayerCollection> = playerCollections
    fun getPlayer(playerId: Long) = tmnt_players.find { it.id == playerId }!!
    fun getCart() = tmnt_players.subList(0,2)
}

private val cart = listOf(
    OrderLine(tmnt_players[4], 2),
    OrderLine(tmnt_players[6], 3),
    OrderLine(tmnt_players[8], 1)
)

@Immutable
data class OrderLine(
    val player: Player,
    val count: Int
)

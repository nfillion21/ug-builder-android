package pgm.poolp.ugbuilder.model

import androidx.compose.runtime.Immutable

@Immutable
data class PlayerCollection(
    val id: Long,
    val name: String,
    val players: List<Player>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, Highlight }

/**
 * A fake repo
 */
object PlayerRepo {
    fun getPlayers(): List<PlayerCollection> = playerCollections
    fun getPlayer(playerId: Long) = tmnt_players.find { it.id == playerId }!!
    fun getCart() = tmnt_players.subList(0,2)
}

/**
 * Static data
 */

private val turtles = PlayerCollection(
    id = 1L,
    name = "Heroes",
    type = CollectionType.Highlight,
    players = tmnt_players.subList(0, 4)
)

private val allies = PlayerCollection(
    id = 2L,
    name = "Allies",
    players = tmnt_players.subList(4, 7)
)

private val ennemies = PlayerCollection(
    id = 2L,
    name = "Ennemies",
    players = tmnt_players.subList(7, 11)
)

private val wfhFavs = turtles.copy(
    id = 3L,
    name = "WFH favourites"
)

private val playerCollections = listOf(
    turtles,
    allies,
    ennemies
    //newlyAdded,
    //exclusive
)

private val related = listOf(
    wfhFavs,
    allies
)

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

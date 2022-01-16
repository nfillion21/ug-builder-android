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
    fun getSnack(snackId: Long) = players.find { it.id == snackId }!!
    fun getRelated(@Suppress("UNUSED_PARAMETER") snackId: Long) = related
    //fun getInspiredByCart() = inspiredByCart
    fun getFilters() = filters
    fun getCart() = cart
}

/**
 * Static data
 */

private val turtles = PlayerCollection(
    id = 1L,
    name = "Heroes",
    type = CollectionType.Highlight,
    players = players.subList(0, 4)
)

private val allies = PlayerCollection(
    id = 2L,
    name = "Allies",
    players = players.subList(4, 7)
)

private val ennemies = PlayerCollection(
    id = 2L,
    name = "Ennemies",
    players = players.subList(7, 11)
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
    OrderLine(players[4], 2),
    OrderLine(players[6], 3),
    OrderLine(players[8], 1)
)

@Immutable
data class OrderLine(
    val player: Player,
    val count: Int
)

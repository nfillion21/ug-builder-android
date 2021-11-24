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
    fun getSnack(snackId: Long) = snacks.find { it.id == snackId }!!
    fun getRelated(@Suppress("UNUSED_PARAMETER") snackId: Long) = related
    fun getInspiredByCart() = inspiredByCart
    fun getFilters() = filters
    fun getCart() = cart
}

/**
 * Static data
 */

private val tastyTreats = PlayerCollection(
    id = 1L,
    name = "Android's picks",
    type = CollectionType.Highlight,
    players = snacks.subList(0, 13)
)

private val popular = PlayerCollection(
    id = 2L,
    name = "Popular on Jetsnack",
    players = snacks.subList(14, 19)
)

private val wfhFavs = tastyTreats.copy(
    id = 3L,
    name = "WFH favourites"
)

private val newlyAdded = popular.copy(
    id = 4L,
    name = "Newly Added"
)

private val exclusive = tastyTreats.copy(
    id = 5L,
    name = "Only on Jetsnack"
)

private val also = tastyTreats.copy(
    id = 6L,
    name = "Customers also bought"
)

private val inspiredByCart = tastyTreats.copy(
    id = 7L,
    name = "Inspired by your cart"
)

private val playerCollections = listOf(
    tastyTreats,
    popular,
    wfhFavs,
    newlyAdded,
    exclusive
)

private val related = listOf(
    also,
    popular
)

private val cart = listOf(
    OrderLine(snacks[4], 2),
    OrderLine(snacks[6], 3),
    OrderLine(snacks[8], 1)
)

@Immutable
data class OrderLine(
        val player: Player,
        val count: Int
)

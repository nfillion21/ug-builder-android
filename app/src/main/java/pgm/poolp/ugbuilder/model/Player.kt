package pgm.poolp.ugbuilder.model

import androidx.compose.runtime.Immutable

@Immutable
data class Player(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val tagline: String = "",
    val tags: Set<String> = emptySet()
)

/**
 * Static data
 */

val players = listOf(
    Player(
        id = 1L,
        name = "Leonardo",
        tagline = "A tag line",
        imageUrl = "https://static.independent.co.uk/s3fs-public/thumbnails/image/2020/04/17/14/teenage-mutant-ninja-turtles-film.jpg?width=982&height=726&auto=webp&quality=75",
        price = 299
    ),
    Player(
        id = 2L,
        name = "Michelangelo",
        tagline = "A tag line",
        imageUrl = "https://d1nslcd7m2225b.cloudfront.net/Pictures/1024x536/6/7/1/1201671_Teenage-Mutant-Ninja-Turtles-1.jpg",
        price = 299
    ),
    Player(
        id = 3L,
        name = "Donatello",
        tagline = "A tag line",
        imageUrl = "https://wallpaperaccess.com/full/124301.jpg",
        price = 299
    ),
    Player(
        id = 4L,
        name = "Raphael",
        tagline = "A tag line",
        imageUrl = "https://i.ytimg.com/vi/G0NjKoVPd74/maxresdefault.jpg",
        price = 299
    ),

    Player(
        id = 5L,
        name = "Splinter",
        tagline = "A tag line",
        imageUrl = "https://a1cf74336522e87f135f-2f21ace9a6cf0052456644b80fa06d4f.ssl.cf2.rackcdn.com/images/characters/large/800/Splinter.Teenage-Mutant-Ninja-Turtles.webp",
        price = 499
    ),
    Player(
        id = 6L,
        name = "April O'Neil",
        tagline = "A tag line",
        imageUrl = "https://static0.cbrimages.com/wordpress/wp-content/uploads/2018/04/April-ONeil.jpg",
        price = 299
    ),
    Player(
        id = 7L,
        name = "Casey Jones",
        tagline = "A tag line",
        imageUrl = "https://i.pinimg.com/originals/c3/f2/8b/c3f28bce41bb0790ccf9ebb80d8370bd.jpg",
        price = 1299
    ),

    Player(
        id = 8L,
        name = "Shredder",
        tagline = "A tag line",
        imageUrl = "https://www.blacksbricks.de/images/product_images/original_images/shredderpcs11.jpg",
        price = 299
    ),
    Player(
        id = 9L,
        name = "Krang",
        tagline = "A tag line",
        imageUrl = "https://sm.ign.com/t/ign_za/news/w/will-krang/will-krang-be-in-ninja-turtles-2_qah3.1280.jpg",
        price = 549
    ),
    Player(
        id = 10L,
        name = "Bebop",
        tagline = "A tag line",
        imageUrl = "https://images.goodsmile.info/cgm/images/product/20161207/6119/42749/large/ada4ef72d300fddf0759c27cdcb2d18b.jpg",
        price = 299
    ),
    Player(
        id = 11L,
        name = "Rocksteady",
        tagline = "A tag line",
        imageUrl = "https://cdn.shopify.com/s/files/1/0059/2602/7298/products/TMNT_Rocksteady_Hero_2048x2048_2048x2048_d377fbec-325e-4d9c-9040-a5c55f571fac.png?v=1593142006",
        price = 299
    ),
    Player(
        id = 12L,
        name = "Nougat",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/qRE_OpbVPR8",
        price = 299
    ),
    Player(
        id = 13L,
        name = "Oreo",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/33fWPnyN6tU",
        price = 299
    ),
    Player(
        id = 14L,
        name = "Pie",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/aX_ljOOyWJY",
        price = 299
    ),
    Player(
        id = 15L,
        name = "Chips",
        imageUrl = "https://source.unsplash.com/UsSdMZ78Q3E",
        price = 299
    ),
    Player(
        id = 16L,
        name = "Pretzels",
        imageUrl = "https://source.unsplash.com/7meCnGCJ5Ms",
        price = 299
    ),
    Player(
        id = 17L,
        name = "Smoothies",
        imageUrl = "https://source.unsplash.com/m741tj4Cz7M",
        price = 299
    ),
    Player(
        id = 18L,
        name = "Popcorn",
        imageUrl = "https://source.unsplash.com/iuwMdNq0-s4",
        price = 299
    ),
    Player(
        id = 19L,
        name = "Almonds",
        imageUrl = "https://source.unsplash.com/qgWWQU1SzqM",
        price = 299
    ),
    Player(
        id = 20L,
        name = "Cheese",
        imageUrl = "https://source.unsplash.com/9MzCd76xLGk",
        price = 299
    ),
    Player(
        id = 21L,
        name = "Apples",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/1d9xXWMtQzQ",
        price = 299
    ),
    Player(
        id = 22L,
        name = "Apple sauce",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/wZxpOw84QTU",
        price = 299
    ),
    Player(
        id = 23L,
        name = "Apple chips",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/okzeRxm_GPo",
        price = 299
    ),
    Player(
        id = 24L,
        name = "Apple juice",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/l7imGdupuhU",
        price = 299
    ),
    Player(
        id = 25L,
        name = "Apple pie",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/bkXzABDt08Q",
        price = 299
    ),
    Player(
        id = 26L,
        name = "Grapes",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/y2MeW00BdBo",
        price = 299
    ),
    Player(
        id = 27L,
        name = "Kiwi",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/1oMGgHn-M8k",
        price = 299
    ),
    Player(
        id = 28L,
        name = "Mango",
        tagline = "A tag line",
        imageUrl = "https://source.unsplash.com/TIGDsyy0TK4",
        price = 299
    )
)

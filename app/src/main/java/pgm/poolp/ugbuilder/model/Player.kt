package pgm.poolp.ugbuilder.model

import androidx.compose.runtime.Immutable

@Immutable
data class Player(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val side: String,
)

/**
 * Static data
 */

val tmnt_players = listOf(
    Player(
        id = 1L,
        name = "Leonardo",
        side = "Hero",
        imageUrl = "https://static.independent.co.uk/s3fs-public/thumbnails/image/2020/04/17/14/teenage-mutant-ninja-turtles-film.jpg?width=982&height=726&auto=webp&quality=75",
        price = 299
    ),
    Player(
        id = 2L,
        name = "Michelangelo",
        side = "Hero",
        imageUrl = "https://d1nslcd7m2225b.cloudfront.net/Pictures/1024x536/6/7/1/1201671_Teenage-Mutant-Ninja-Turtles-1.jpg",
        price = 299
    ),
    Player(
        id = 3L,
        name = "Donatello",
        side = "Hero",
        imageUrl = "https://wallpaperaccess.com/full/124301.jpg",
        price = 299
    ),
    Player(
        id = 4L,
        name = "Raphael",
        side = "Hero",
        imageUrl = "https://i.ytimg.com/vi/G0NjKoVPd74/maxresdefault.jpg",
        price = 299
    ),

    Player(
        id = 5L,
        name = "Splinter",
        side = "Ally",
        imageUrl = "https://a1cf74336522e87f135f-2f21ace9a6cf0052456644b80fa06d4f.ssl.cf2.rackcdn.com/images/characters/large/800/Splinter.Teenage-Mutant-Ninja-Turtles.webp",
        price = 499
    ),
    Player(
        id = 6L,
        name = "April O'Neil",
        side = "Ally",
        imageUrl = "https://static0.cbrimages.com/wordpress/wp-content/uploads/2018/04/April-ONeil.jpg",
        price = 299
    ),
    Player(
        id = 7L,
        name = "Casey Jones",
        side = "Ally",
        imageUrl = "https://i.pinimg.com/originals/c3/f2/8b/c3f28bce41bb0790ccf9ebb80d8370bd.jpg",
        price = 1299
    ),

    Player(
        id = 8L,
        name = "Shredder",
        side = "Villain",
        imageUrl = "https://www.blacksbricks.de/images/product_images/original_images/shredderpcs11.jpg",
        price = 299
    ),
    Player(
        id = 9L,
        name = "Krang",
        side = "Villain",
        imageUrl = "https://sm.ign.com/t/ign_za/news/w/will-krang/will-krang-be-in-ninja-turtles-2_qah3.1280.jpg",
        price = 549
    ),
    Player(
        id = 10L,
        name = "Bebop",
        side = "Villain",
        imageUrl = "https://images.goodsmile.info/cgm/images/product/20161207/6119/42749/large/ada4ef72d300fddf0759c27cdcb2d18b.jpg",
        price = 299
    ),
    Player(
        id = 11L,
        name = "Rocksteady",
        side = "Villain",
        imageUrl = "https://cdn.shopify.com/s/files/1/0059/2602/7298/products/TMNT_Rocksteady_Hero_2048x2048_2048x2048_d377fbec-325e-4d9c-9040-a5c55f571fac.png?v=1593142006",
        price = 299
    )
)

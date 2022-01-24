package pgm.poolp.ugbuilder.ui.players

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.statusBarsPadding
import pgm.poolp.ugbuilder.model.PlayerCollectionData
import pgm.poolp.ugbuilder.ui.components.JetsnackSurface
import pgm.poolp.ugbuilder.ui.components.PlayerCollection
import pgm.poolp.ugbuilder.viewmodels.HeroViewModel

@Composable
fun Oreo(
    heroViewModel:HeroViewModel,
    selectPlayer: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    JetsnackSurface(
        modifier = modifier.fillMaxSize()) {
        Box {
            playerCollectionList(heroViewModel, selectPlayer)
        }
    }
}

@Composable
private fun playerCollectionList(
    heroViewModel: HeroViewModel,
    selectPlayer: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val heroes_ by heroViewModel.allHeroes.observeAsState(listOf())
    val allies_ by heroViewModel.allAllies.observeAsState(listOf())
    val villains_ by heroViewModel.allVillains.observeAsState(listOf())

    val turtles = PlayerCollectionData(
        id = 1L,
        name = "Heroes",
        players = heroes_
    )

    val allies = PlayerCollectionData(
        id = 2L,
        name = "Allies",
        players = allies_
    )

    val villains = PlayerCollectionData(
        id = 3L,
        name = "Villains",
        players = villains_
    )

    LazyColumn(modifier
        .statusBarsPadding()
    ) {
        item {
            CoursesAppBar()
        }
        itemsIndexed(listOf(turtles,allies,villains)) { _, playerCollection ->
            PlayerCollection(
                playerCollection = playerCollection,
                selectPlayer = selectPlayer
            )
        }
    }
}
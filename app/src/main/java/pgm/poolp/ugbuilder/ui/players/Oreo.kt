package pgm.poolp.ugbuilder.ui.players

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import pgm.poolp.ugbuilder.model.Player
import pgm.poolp.ugbuilder.model.PlayerCollection
import pgm.poolp.ugbuilder.model.PlayerRepo
import pgm.poolp.ugbuilder.ui.cart.CartViewModel
import pgm.poolp.ugbuilder.ui.components.JetsnackSurface
import pgm.poolp.ugbuilder.ui.components.PlayerCollection

@Composable
fun Oreo(
    selectPlayer: (Long) -> Unit,
    viewModel: CartViewModel = viewModel(factory = CartViewModel.provideFactory()),
    modifier: Modifier = Modifier
) {
    val playerCollections = remember { PlayerRepo.getPlayers() }
    Oreo(
        playerCollections,
        selectPlayer,
        addPlayer = viewModel::addPlayer,
        modifier
    )
}

@Composable
private fun Oreo(
    playerCollections: List<PlayerCollection>,
    onPlayerClick: (Long) -> Unit,
    addPlayer: (Player) -> Unit,
    modifier: Modifier = Modifier
) {

    JetsnackSurface(
        modifier = modifier.fillMaxSize()) {
        Box {
            playerCollectionList(playerCollections, onPlayerClick, addPlayer)
        }
    }
}

@Composable
private fun playerCollectionList(
    playerCollections: List<PlayerCollection>,
    onPlayerClick: (Long) -> Unit,
    addPlayer: (Player) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier
        .statusBarsPadding()
    ) {
        item {
            CoursesAppBar()
        }
        itemsIndexed(playerCollections) { _, playerCollection ->
            PlayerCollection(
                playerCollection = playerCollection,
                onPlayerClick = onPlayerClick,
                addPlayer = addPlayer,
            )
        }
    }
}
package pgm.poolp.ugbuilder.ui.players

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import pgm.poolp.ugbuilder.model.PlayerCollection
import pgm.poolp.ugbuilder.model.PlayerRepo
import pgm.poolp.ugbuilder.ui.components.JetsnackSurface
import pgm.poolp.ugbuilder.ui.components.PlayerCollection

@Composable
fun LaunchOreo(
    selectPlayer: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val playerCollections = remember { PlayerRepo.getPlayers() }
    LaunchOreo(
        playerCollections,
        selectPlayer,
        modifier
    )
}

@Composable
private fun LaunchOreo(
    playerCollections: List<PlayerCollection>,
    onPlayerClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    JetsnackSurface(
        modifier = modifier.fillMaxSize()) {
        Box {
            playerCollectionList(playerCollections, onPlayerClick)
        }
    }
}

@Composable
private fun playerCollectionList(
    playerCollections: List<PlayerCollection>,
    onPlayerClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight(additional = 56.dp))
        }
        itemsIndexed(playerCollections) { _, playerCollection ->
            PlayerCollection(
                playerCollection = playerCollection,
                onPlayerClick = onPlayerClick,
            )
        }
    }
}
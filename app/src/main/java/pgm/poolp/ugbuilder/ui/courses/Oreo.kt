package pgm.poolp.ugbuilder.ui.courses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.google.accompanist.insets.statusBarsHeight
import pgm.poolp.ugbuilder.model.Filter
import pgm.poolp.ugbuilder.model.PlayerCollection
import pgm.poolp.ugbuilder.model.PlayerRepo
import pgm.poolp.ugbuilder.ui.components.FilterBar
import pgm.poolp.ugbuilder.ui.components.JetsnackSurface
import pgm.poolp.ugbuilder.ui.components.PlayerCollection
import pgm.poolp.ugbuilder.ui.components.UGBuilderDivider
import pgm.poolp.ugbuilder.ui.theme.BlueTheme

@Composable
fun Oreo(
    onPlayerClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val playerCollections = remember { PlayerRepo.getPlayers() }
    val filters = remember { PlayerRepo.getFilters() }
    Oreo(
        playerCollections,
        filters,
        onPlayerClick,
        modifier
    )
}

@Composable
private fun Oreo(
    playerCollections: List<PlayerCollection>,
    filters: List<Filter>,
    onPlayerClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    JetsnackSurface(
        modifier = modifier.fillMaxSize()) {
        Box {
            playerCollectionList(playerCollections, onPlayerClick)
            //DestinationBar()
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
        itemsIndexed(playerCollections) { index, playerCollection ->
            PlayerCollection(
                playerCollection = playerCollection,
                onPlayerClick = onPlayerClick,
                index = index
            )
        }
    }
}
package pgm.poolp.ugbuilder.ui.courses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    //onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val playerCollections = remember { PlayerRepo.getPlayers() }
    val filters = remember { PlayerRepo.getFilters() }
    Oreo(
        playerCollections,
        filters,
        //onSnackClick,
        modifier
    )
}

@Composable
private fun Oreo(
    playerCollections: List<PlayerCollection>,
    filters: List<Filter>,
    //onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    /*
    Surface(
        modifier = modifier.fillMaxSize()) {
        Box {
            playerCollectionList(playerCollections)//, onSnackClick)
            //DestinationBar()
        }
    }
     */

    JetsnackSurface(
        modifier = modifier.fillMaxSize()) {
        Box {
            playerCollectionList(playerCollections)//, onSnackClick)
            //DestinationBar()
        }
    }
}

@Composable
private fun playerCollectionList(
    playerCollections: List<PlayerCollection>,
    //onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight(additional = 56.dp))
        }
        itemsIndexed(playerCollections) { index, playerCollection ->
            /*
            if (index > 0) {
                UGBuilderDivider(thickness = 2.dp)
            }
            */
            PlayerCollection(
                playerCollection = playerCollection,
                //onSnackClick = onSnackClick,
                index = index
            )
        }
    }
}
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
import com.google.accompanist.insets.statusBarsHeight
import pgm.poolp.ugbuilder.model.Filter
import pgm.poolp.ugbuilder.model.SnackCollection
import pgm.poolp.ugbuilder.model.SnackRepo
import pgm.poolp.ugbuilder.ui.components.FilterBar
import pgm.poolp.ugbuilder.ui.components.JetsnackSurface
import pgm.poolp.ugbuilder.ui.components.SnackCollection
import pgm.poolp.ugbuilder.ui.components.UGBuilderDivider

@Composable
fun Feed(
    //onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val snackCollections = remember { SnackRepo.getSnacks() }
    val filters = remember { SnackRepo.getFilters() }
    Feed(
        snackCollections,
        filters,
        //onSnackClick,
        modifier
    )
}

@Composable
private fun Feed(
    snackCollections: List<SnackCollection>,
    filters: List<Filter>,
    //onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    JetsnackSurface(modifier = modifier.fillMaxSize()) {
        Box {
            SnackCollectionList(snackCollections, filters)//, onSnackClick)
            //DestinationBar()
        }
    }
}

@Composable
private fun SnackCollectionList(
    snackCollections: List<SnackCollection>,
    filters: List<Filter>,
    //onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight(additional = 56.dp))
            FilterBar(filters)
        }
        itemsIndexed(snackCollections) { index, snackCollection ->
            if (index > 0) {
                UGBuilderDivider(thickness = 2.dp)
            }
            SnackCollection(
                snackCollection = snackCollection,
                //onSnackClick = onSnackClick,
                index = index
            )
        }
    }
}

/*
@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun HomePreview() {
    JetsnackTheme {
        Feed(onSnackClick = { })
    }
}
*/
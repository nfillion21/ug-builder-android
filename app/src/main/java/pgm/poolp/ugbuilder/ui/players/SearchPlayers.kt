package pgm.poolp.ugbuilder.ui.players

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsPadding
import pgm.poolp.ugbuilder.model.Player
import pgm.poolp.ugbuilder.viewmodels.HeroViewModel

@Composable
fun SearchPlayers(
    players: List<Player>,
    heroViewModel: HeroViewModel,
    modifier: Modifier = Modifier
) {
    val newHeroViewModel:HeroViewModel = viewModel()
    //val heroes = newHeroViewModel.allHeroes.observeAsState()
    val heroes by newHeroViewModel.allHeroes.observeAsState(listOf())
    LazyColumn(modifier = modifier.statusBarsPadding()) {
        item {
            CoursesAppBar()
        }
        items(heroes) { hero ->
            Text(
                text = hero.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { /* todo */ })
                    .padding(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                    .wrapContentWidth(Alignment.Start)
            )
        }
    }
}
package pgm.poolp.ugbuilder.ui.players

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import pgm.poolp.ugbuilder.R
import pgm.poolp.ugbuilder.model.Player
import pgm.poolp.ugbuilder.model.Team

@Composable
fun SearchPlayers(
    players: List<Player>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.statusBarsPadding()) {
        item {
            CoursesAppBar()
        }
        items(players) { player ->
            Text(
                text = player.name,
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
package pgm.poolp.ugbuilder.ui.players

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import pgm.poolp.ugbuilder.database.Hero
import pgm.poolp.ugbuilder.preferences.SortOrder
import pgm.poolp.ugbuilder.viewmodels.HeroViewModel
import pgm.poolp.ugbuilder.viewmodels.PlayersUiModel

@Composable
fun SearchPlayers(
    heroViewModel: HeroViewModel,
    modifier: Modifier = Modifier
) {
    val statePlayers:PlayersUiModel? by heroViewModel.playersUiModel.collectAsState()
    val players = statePlayers?.players ?: emptyList()

    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .statusBarsPadding())
    {
        LazyColumn(modifier = Modifier) {
            item {
                CoursesAppBar()
            }

            item {

                Row(modifier = Modifier.padding(all = 16.dp)) {
                    Icon(
                        imageVector = Icons.Default.Reorder,
                        contentDescription = null,
                        tint = LocalContentColor.current.copy(alpha = 0.5f),
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    SortChip(
                        text = "Priority",
                        selected = true,
                        //setSelected = ,
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    SortChip(
                        text = "Deadline",
                        selected = true,
                        //setSelected = ,
                        shape = RoundedCornerShape(14.dp)
                    )
                }
            }

            item {

                Row(modifier = Modifier.padding(all = 16.dp)) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = null,
                        tint = LocalContentColor.current.copy(alpha = 0.5f),
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        text = "Show completed tasks",
                        style = MaterialTheme.typography.subtitle1
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Checkbox(
                        checked = true,
                        onCheckedChange = null
                    )
                }
            }

            items(players) { hero ->
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
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SortChip(
    text: String,
    selected: Boolean,
    //setSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
) {
    Surface(
        modifier = modifier.height(28.dp),
        color = MaterialTheme.colors.secondary,
        shape = shape,
        elevation = 2.dp
    ) {
        Box(
            modifier = Modifier.toggleable(
                value = selected,
                onValueChange = {
                    println("Toggled!")
                },
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 6.dp
                )
            ) {
                AnimatedVisibility(visible = selected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.width(24.dp),
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                }

                Text(
                    text = text,
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,

                    )
            }
        }
    }
}

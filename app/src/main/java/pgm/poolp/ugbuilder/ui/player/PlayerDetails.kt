package pgm.poolp.ugbuilder.ui.player

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pgm.poolp.ugbuilder.R
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import java.util.Locale
import kotlinx.coroutines.launch
import pgm.poolp.ugbuilder.data.Hero
import pgm.poolp.ugbuilder.ui.common.OutlinedAvatar
import pgm.poolp.ugbuilder.ui.theme.PinkTheme
import pgm.poolp.ugbuilder.ui.utils.NetworkImage
import pgm.poolp.ugbuilder.ui.utils.scrim
import pgm.poolp.ugbuilder.viewmodels.CartViewModel
import pgm.poolp.ugbuilder.viewmodels.HeroViewModel

private val FabSize = 56.dp
private const val ExpandedSheetAlpha = 0.96f

@Composable
fun PlayerDetails(
    playerId: String,
    playerViewModel: HeroViewModel,
    cartViewModel: CartViewModel,
    addToCart: (String) -> Unit,
    upPress: () -> Unit
) {
    val playerBis by playerViewModel.player(playerId).observeAsState()
    PlayerDetails(playerBis, addToCart, upPress)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerDetails(
    player: Hero?,
    addToCart: (String) -> Unit,
    upPress: () -> Unit
) {
    PinkTheme {
        BoxWithConstraints {
            val sheetState = rememberSwipeableState(SheetState.Closed)
            val fabSize = with(LocalDensity.current) { FabSize.toPx() }
            val dragRange = constraints.maxHeight - fabSize
            val scope = rememberCoroutineScope()

            BackHandler(
                enabled = sheetState.currentValue == SheetState.Open,
                onBack = {
                    scope.launch {
                        sheetState.animateTo(SheetState.Closed)
                    }
                }
            )

            Box(
                // The Lessons sheet is initially closed and appears as a FAB. Make it openable by
                // swiping or clicking the FAB.
                Modifier.swipeable(
                    state = sheetState,
                    anchors = mapOf(
                        0f to SheetState.Closed,
                        -dragRange to SheetState.Open
                    ),
                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                    orientation = Vertical
                )
            ) {
                val openFraction = if (sheetState.offset.value.isNaN()) {
                    0f
                } else {
                    -sheetState.offset.value / dragRange
                }.coerceIn(0f, 1f)
                if (player != null)
                {
                    CourseDescription(player, addToCart, upPress)
                }
            }
        }
    }
}

@Composable
private fun CourseDescription(
    player: Hero,
    addToCart: (String) -> Unit,
    upPress: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item { CourseDescriptionHeader(player, upPress) }
            item { CourseDescriptionBody(player) }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .navigationBarsPadding()
                        .padding(
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp,
                            bottom = 24.dp
                        )
,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        modifier = Modifier,
                        onClick = { addToCart(player.heroId) },
                        // Uses ButtonDefaults.ContentPadding by default
                        contentPadding = PaddingValues(
                            start = 20.dp,
                            top = 12.dp,
                            end = 20.dp,
                            bottom = 12.dp
                        )
                    ) {
                        // Inner content including an icon and a text label
                        Icon(
                            Icons.Filled.AddShoppingCart,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Add to cart")
                    }
                }
            }

            /*
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            */
        }
    }
}

@Composable
private fun CourseDescriptionHeader(
    player: Hero,
    upPress: () -> Unit
) {
    Box {
        NetworkImage(
            url = player.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .scrim(colors = listOf(Color(0x80000000), Color(0x33000000)))
                .aspectRatio(4f / 3f)
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White, // always white as image has dark scrim
            modifier = Modifier.statusBarsPadding()
        ) {
            IconButton(onClick = upPress) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.label_back)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        OutlinedAvatar(
            url = player.imageUrl,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 20.dp) // overlap bottom of image
        )
    }
}

@Composable
private fun CourseDescriptionBody(player: Hero) {
    Text(
        //text = player.subject.uppercase(Locale.getDefault()),
        text = player.side.uppercase(Locale.getDefault()),
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 36.dp,
                end = 16.dp,
                bottom = 16.dp
            )
    )
    Text(
        text = player.name,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = stringResource(id = R.string.course_desc),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
    Divider(modifier = Modifier.padding(16.dp))
}
private enum class SheetState { Open, Closed }

private val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0
package pgm.poolp.ugbuilder.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.rounded.OndemandVideo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import pgm.poolp.ugbuilder.R
import pgm.poolp.ugbuilder.model.Player
import pgm.poolp.ugbuilder.model.PlayerCollection
import pgm.poolp.ugbuilder.ui.common.OutlinedAvatar
import pgm.poolp.ugbuilder.ui.theme.*
import pgm.poolp.ugbuilder.ui.utils.NetworkImage
import pgm.poolp.ugbuilder.ui.utils.mirroringIcon
import java.util.*

private val HighlightCardWidth = 170.dp
private val HighlightCardPadding = 16.dp

// The Cards show a gradient which spans 3 cards and scrolls with parallax.
private val gradientWidth
    @Composable
    get() = with(LocalDensity.current) {
        (3 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }

@Composable
fun PlayerCollection(
        playerCollection: PlayerCollection,
    //onSnackClick: (Long) -> Unit,
        modifier: Modifier = Modifier,
        index: Int = 0,
) {
    Column(modifier = modifier) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(start = 44.dp)
        ) {

            Text(
                text = playerCollection.name,
                style = MaterialTheme.typography.h6,
                color = UGBuilderTheme.colors.secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            IconButton(
                onClick = { /* todo */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = mirroringIcon(
                        ltrIcon = Icons.Outlined.ArrowForward,
                        rtlIcon = Icons.Outlined.ArrowBack
                    ),
                    tint = UGBuilderTheme.colors.primary,
                    contentDescription = null
                )
            }
        }
        /*
        if (highlight && playerCollection.type == CollectionType.Highlight) {
            HighlightedSnacks(index, playerCollection.players/*, onSnackClick*/)
        }
         */
        HighlightedPlayers(index, playerCollection.players/*, onSnackClick*/)
    }
}

@Composable
private fun HighlightedPlayers(
        index: Int,
        players: List<Player>,
    //onSnackClick: (Long) -> Unit,
        modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState(0)
    val gradient = when ((index / 2) % 2) {
        0 -> listOf(Shadow5, Ocean7, Shadow9, Ocean7, Shadow5)
        else -> listOf(Rose4, Lavender3, Rose2, Lavender3, Rose4)
    }
    // The Cards show a gradient which spans 3 cards and scrolls with parallax.
    val gradientWidth = with(LocalDensity.current) {
        (6 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }
    LazyRow(
        modifier = modifier,
        //horizontalArrangement = Arrangement.spacedBy(16.dp),
        //contentPadding = PaddingValues(start = 24.dp, end = 24.dp),
    ) {
        itemsIndexed(players) { index, player ->
            /*
            HighlightPlayerItem(
                snack,
                //onSnackClick,
                index,
                gradient,
                gradientWidth,
                scroll.value
            )
            */
            PlayerItem(player = player)
        }
    }
}

/*
@Composable
private fun HighlightPlayerItem(
        player: Player,
    //onSnackClick: (Long) -> Unit,
        index: Int,
        gradient: List<Color>,
        gradientWidth: Float,
        scroll: Int,
        modifier: Modifier = Modifier
) {
    val left = index * with(LocalDensity.current) {
        (HighlightCardWidth + HighlightCardPadding).toPx()
    }
    JetsnackCard(
        modifier = modifier
            .size(
                width = 170.dp,
                height = 250.dp
            )
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { /*onSnackClick(snack.id)*/ })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {
                val gradientOffset = left - (scroll / 3f)
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .offsetGradientBackground(gradient, gradientWidth, gradientOffset)
                )
                PlayerImage(
                    imageUrl = player.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = player.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = UGBuilderTheme.colors.primary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = player.tagline,
                style = MaterialTheme.typography.body1,
                color = UGBuilderTheme.colors.primary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
*/


@Composable
fun PlayerItem(
    player: Player,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            //.fillMaxHeight()
            .width(170.dp),
                /*
            .size(
                width = 170.dp,
                height = 250.dp
            ),
        */
        color = MaterialTheme.colors.surface,
        elevation = UGBuilderTheme.elevations.card,
        shape = MaterialTheme.shapes.medium,
    ) {
        val featuredString = stringResource(id = R.string.featured)
        ConstraintLayout(
            modifier = Modifier
                .clickable(
                    onClick = { /*selectCourse(course.id)*/ "id" }
                )
                .semantics {
                    contentDescription = featuredString
                }
        ) {
            val (image, avatar, subject, name, steps, icon) = createRefs()

            NetworkImage(
                url = player.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(4f / 3f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
            )

            val outlineColor = LocalElevationOverlay.current?.apply(
                color = MaterialTheme.colors.surface,
                elevation = UGBuilderTheme.elevations.card
            ) ?: MaterialTheme.colors.surface
            OutlinedAvatar(
                //url = course.instructor,
                url = player.imageUrl,
                outlineColor = outlineColor,
                modifier = Modifier
                    .size(38.dp)
                    .constrainAs(avatar) {
                        centerHorizontallyTo(parent)
                        centerAround(image.bottom)
                    }
            )
            Text(
                //text = course.subject.uppercase(Locale.getDefault()),
                text = "Arts & Crafts",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.overline,
                modifier = Modifier
                    .padding(16.dp)
                    .constrainAs(subject) {
                        centerHorizontallyTo(parent)
                        top.linkTo(avatar.bottom)
                    }
            )
            Text(
                //text = course.name,
                text = player.name,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        top.linkTo(subject.bottom)
                    }
            )
            val center = createGuidelineFromStart(0.5f)
            Icon(
                imageVector = Icons.Rounded.OndemandVideo,
                tint = MaterialTheme.colors.primary,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .constrainAs(icon) {
                        end.linkTo(center)
                        centerVerticallyTo(steps)
                    }
            )
            Text(
                //text = course.steps.toString(),
                text = "1",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
                    .constrainAs(steps) {
                        start.linkTo(center)
                        top.linkTo(name.bottom)
                    }
            )
        }
    }
}

/*
@Composable
fun PlayerImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    JetsnackSurface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    crossfade(true)
                    placeholder(drawableResId = R.drawable.placeholder)
                }
            ),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}
*/
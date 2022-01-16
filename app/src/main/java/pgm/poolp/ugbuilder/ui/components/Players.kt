package pgm.poolp.ugbuilder.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Money
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import pgm.poolp.ugbuilder.R
import pgm.poolp.ugbuilder.model.Player
import pgm.poolp.ugbuilder.model.PlayerCollection
import pgm.poolp.ugbuilder.ui.common.OutlinedAvatar
import pgm.poolp.ugbuilder.ui.theme.*
import pgm.poolp.ugbuilder.ui.utils.NetworkImage
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
        onPlayerClick: (Long) -> Unit,
        modifier: Modifier = Modifier
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
                color = UGBuilderTheme.colors.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
        }
        HighlightedPlayers(playerCollection.players, onPlayerClick)
    }
}

@Composable
private fun HighlightedPlayers(
        players: List<Player>,
        onPlayerClick: (Long) -> Unit,
        modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        //horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp),
    ) {
        itemsIndexed(players) { _, player ->
            PlayerItem(
                player = player,
                onPlayerClick = onPlayerClick
            )
        }
    }
}

@Composable
fun PlayerItem(
    player: Player,
    onPlayerClick: (Long) -> Unit,
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
                    onClick = { onPlayerClick(player.id)}
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
                //text = "Arts & Crafts",
                text = player.side,
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
                imageVector = Icons.Rounded.Money,
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
                //text = course.steps.toString(), price
                text = "100",
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
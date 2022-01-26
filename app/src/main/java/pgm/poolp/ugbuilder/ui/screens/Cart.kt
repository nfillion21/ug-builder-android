package pgm.poolp.ugbuilder.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsPadding
import pgm.poolp.ugbuilder.R
import pgm.poolp.ugbuilder.data.CartWithPlayers
import pgm.poolp.ugbuilder.data.Hero
import pgm.poolp.ugbuilder.ui.common.OutlinedAvatar
import pgm.poolp.ugbuilder.ui.components.JetsnackSurface
import pgm.poolp.ugbuilder.ui.theme.UGBuilderTheme
import pgm.poolp.ugbuilder.ui.utils.NetworkImage
import pgm.poolp.ugbuilder.viewmodels.CartViewModel
import pgm.poolp.ugbuilder.viewmodels.HeroViewModel


@Composable
fun Cart(
    cartWithPlayers: CartWithPlayers?,
    removeFromCart: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier
        .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
        item {
            CoursesAppBar()
        }
        val players = cartWithPlayers?.players ?: listOf()
        items(players) { player ->
            CartItem(
                player = player,
                removePlayer = removeFromCart
            )
        }
    }
}




@Composable
fun PlayerItem(
    player: Hero,
    selectPlayer: (String) -> Unit,
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
                    //onClick = { onPlayerClick(player.id)}
                    onClick = { selectPlayer(player.heroId)}
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


@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    player: Hero,
    removePlayer: (String) -> Unit
) {
    //val snack = orderLine.snack
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            //.fillMaxHeight()
            .height(128.dp)
            .background(Color.White)
            //.clickable { onSnackClick(snack.id) }
            //.padding(horizontal = 24.dp)
    ) {
        val (image, name, tag, priceSpacer, price, remove, icon) = createRefs()
        createVerticalChain(name, tag, priceSpacer, icon, chainStyle = ChainStyle.Packed)
        NetworkImage(
            url = player.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(4f / 3f)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = player.name,
            style = UGBuilderTheme.typography.subtitle1,
            color = Color.Black,
            modifier = Modifier.constrainAs(name) {
                start.linkTo(image.end, margin = 16.dp)
            }
        )
        IconButton(
            onClick = { removePlayer(player.heroId) },
            modifier = Modifier
                .constrainAs(remove) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                tint = UGBuilderTheme.colors.primary,
                contentDescription = ""
                //contentDescription = stringResource(R.string.label_remove)
            )
        }
        Text(
            //text = snack.tagline,
            text = player.side,
            style = UGBuilderTheme.typography.overline,
            color = UGBuilderTheme.colors.primary,
            modifier = Modifier.constrainAs(tag) {
                start.linkTo(image.end, margin = 16.dp)
            }
        )
        Spacer(
            Modifier
                .height(16.dp)
                .constrainAs(priceSpacer) {
                    top.linkTo(tag.bottom)
                    bottom.linkTo(price.top)
                }
        )

        Icon(
            imageVector = Icons.Rounded.Money,
            tint = MaterialTheme.colors.primary,
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .constrainAs(icon) {
                    start.linkTo(image.end, margin = 16.dp)
                }
        )

        Text(
            text = player.price.toString(),
            style = MaterialTheme.typography.subtitle2,
            color = UGBuilderTheme.colors.primary,
            modifier = Modifier
                .padding(
                    start = 4.dp
                )
                .constrainAs(price) {
                    start.linkTo(icon.end)
                    centerVerticallyTo(icon)
                }
        )
    }
}


@Composable
fun SnackImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    JetsnackSurface(
        color = Color.LightGray,
        elevation = elevation,
        //shape = CircleShape,
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

@Composable
fun SummaryItem(
    subtotal: Long,
    shippingCosts: Long,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            //text = stringResource(R.string.cart_summary_header),
            text = "Hello world",
            style = MaterialTheme.typography.h6,
            color = UGBuilderTheme.colors.secondaryVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .heightIn(min = 56.dp)
                .wrapContentHeight()
        )
        Row(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                //text = stringResource(R.string.cart_subtotal_label),
                text = "car subtotal",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
                    .alignBy(LastBaseline)
            )
            Text(
                //text = formatPrice(subtotal),
                text = "subtotal",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(
                //text = stringResource(R.string.cart_shipping_label),
                text = "subtotal",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
                    .alignBy(LastBaseline)
            )
            Text(
                //text = formatPrice(shippingCosts),
                text = "subtotal",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        //JetsnackDivider() look at the divider

        /*
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(
                text = stringResource(R.string.cart_total_label),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .wrapContentWidth(Alignment.End)
                    .alignBy(LastBaseline)
            )
            Text(
                text = formatPrice(subtotal + shippingCosts),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        JetsnackDivider()
        */
    }
}

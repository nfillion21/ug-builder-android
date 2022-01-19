package pgm.poolp.ugbuilder.ui.players

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsPadding
import pgm.poolp.ugbuilder.R
import pgm.poolp.ugbuilder.model.Player
import pgm.poolp.ugbuilder.ui.cart.CartViewModel
import pgm.poolp.ugbuilder.ui.components.JetsnackSurface
import pgm.poolp.ugbuilder.ui.theme.UGBuilderTheme

@Composable
fun Cart(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = viewModel(factory = CartViewModel.provideFactory())
) {
    val players by viewModel.players.collectAsState()
    Cart(
        modifier = modifier,
        players = players,
        removePlayer = viewModel::removePlayer,
        addPlayer = viewModel::addPlayer
    )
}

@Composable
fun Cart(
    modifier: Modifier = Modifier,
    players: List<Player>,
    removePlayer: (Long) -> Unit,
    addPlayer: (Player) -> Unit
) {
    LazyColumn(modifier = modifier
        .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
        item {
            CoursesAppBar()
        }
        items(players) { player ->
            CartItem(
                player = player,
                removePlayer = removePlayer,
                addPlayer = addPlayer
            )
        }
    }
}

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    player: Player,
    removePlayer: (Long) -> Unit,
    addPlayer: (Player) -> Unit
) {
    //val snack = orderLine.snack
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            //.clickable { onSnackClick(snack.id) }
            .padding(horizontal = 24.dp)
    ) {
        val (image, name, tag, priceSpacer, price, remove, icon) = createRefs()
        createVerticalChain(name, tag, priceSpacer, icon, chainStyle = ChainStyle.Packed)
        SnackImage(
            //imageUrl = snack.imageUrl,
            imageUrl = "https://static.independent.co.uk/s3fs-public/thumbnails/image/2020/04/17/14/teenage-mutant-ninja-turtles-film.jpg?width=982&height=726&auto=webp&quality=75",
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
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
            onClick = { removePlayer(player.id) },
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
            //text = formatPrice(snack.price),
            text = player.side,
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

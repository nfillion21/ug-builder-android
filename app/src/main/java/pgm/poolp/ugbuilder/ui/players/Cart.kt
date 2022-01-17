package pgm.poolp.ugbuilder.ui.players

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import pgm.poolp.ugbuilder.R
import pgm.poolp.ugbuilder.model.PlayerCollection
import pgm.poolp.ugbuilder.model.Team
import pgm.poolp.ugbuilder.ui.components.JetsnackSurface
import pgm.poolp.ugbuilder.ui.theme.UGBuilderTheme

@Composable
fun Cart(
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    //val viewModel: CartViewModel = viewModel()
    //val orderLines by viewModel.orderLines.collectAsState()
    //val inspiredByCart = remember { SnackRepo.getInspiredByCart() }
    Cart(
        modifier = modifier
    )
}

@Composable
fun Cart(
    modifier: Modifier = Modifier
) {
    JetsnackSurface(modifier = modifier.fillMaxSize()) {
        Box {
            CartContent(
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}


@Composable
private fun CartContent(
    modifier: Modifier = Modifier
) {
    val resources = LocalContext.current.resources
    /*
    val snackCountFormattedString = remember(orderLines.size, resources) {
        resources.getQuantityString(
            R.plurals.cart_order_count,
            //orderLines.size, orderLines.size
        )
    }
    */
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight(additional = 56.dp))
            Text(
                //text = stringResource(R.string.cart_order_header, snackCountFormattedString),
                text = "Hello world",
                style = MaterialTheme.typography.h6,
                color = UGBuilderTheme.colors.secondaryVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .wrapContentHeight()
            )
        }
        item {
            CartItem()
        }
        /*
        item {
            SummaryItem(
                subtotal = orderLines.map { it.snack.price * it.count }.sum(),
                shippingCosts = 369
            )
        }
        */
        /*
        item {
            PlayerCollection(
                snackCollection = inspiredByCart,
                onSnackClick = onSnackClick,
                highlight = false
            )
            Spacer(Modifier.height(56.dp))
        }
        */
    }
}


@Composable
fun CartItem(
    modifier: Modifier = Modifier
) {
    //val snack = orderLine.snack
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            //.clickable { onSnackClick(snack.id) }
            .padding(horizontal = 24.dp)
    ) {
        val (divider, image, name, tag, priceSpacer, price, remove, quantity) = createRefs()
        createVerticalChain(name, tag, priceSpacer, price, chainStyle = ChainStyle.Packed)
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
            text = "Hello world 2",
            style = MaterialTheme.typography.subtitle1,
            color = UGBuilderTheme.colors.secondaryVariant,
            modifier = Modifier.constrainAs(name) {
                start.linkTo(image.end, margin = 16.dp)
            }
        )
        IconButton(
            onClick = { /*removeSnack(snack.id)*/ },
            modifier = Modifier
                .constrainAs(remove) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                tint = UGBuilderTheme.colors.secondaryVariant,
                contentDescription = ""
                //contentDescription = stringResource(R.string.label_remove)
            )
        }
        Text(
            //text = snack.tagline,
            text = "Hello world tagline",
            style = MaterialTheme.typography.body1,
            color = UGBuilderTheme.colors.secondaryVariant,
            modifier = Modifier.constrainAs(tag) {
                start.linkTo(image.end, margin = 16.dp)
            }
        )
        Spacer(
            Modifier
                .height(8.dp)
                .constrainAs(priceSpacer) {
                    top.linkTo(tag.bottom)
                    bottom.linkTo(price.top)
                }
        )
        Text(
            //text = formatPrice(snack.price),
            text = "Hello world price",
            style = MaterialTheme.typography.subtitle1,
            color = UGBuilderTheme.colors.primary,
            modifier = Modifier.constrainAs(price) {
                start.linkTo(image.end, margin = 16.dp)
            }
        )
        /*
        QuantitySelector(
            count = orderLine.count,
            decreaseItemCount = { decreaseItemCount(snack.id) },
            increaseItemCount = { increaseItemCount(snack.id) },
            modifier = Modifier.constrainAs(quantity) {
                baseline.linkTo(price.baseline)
                end.linkTo(parent.end)
            }
        )
        */
        /*
        JetsnackDivider(
            Modifier.constrainAs(divider) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.bottom)
            }
        )
        */
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

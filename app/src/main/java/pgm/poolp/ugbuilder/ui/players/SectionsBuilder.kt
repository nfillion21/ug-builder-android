package pgm.poolp.ugbuilder.ui.players

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import pgm.poolp.ugbuilder.R
import pgm.poolp.ugbuilder.model.tmnt_players
import pgm.poolp.ugbuilder.ui.MainRoutes
import androidx.hilt.navigation.compose.hiltViewModel
import pgm.poolp.ugbuilder.viewmodels.HeroViewModel
import pgm.poolp.ugbuilder.viewmodels.PlayersUiModel

fun NavGraphBuilder.buildSections(
    onPlayerSelected: (Long, NavBackStackEntry) -> Unit,
    onboardingComplete: State<Boolean>, // https://issuetracker.google.com/174783110
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(BuilderSectionsTabs.SEARCH.route) {

        val heroViewModel:HeroViewModel = hiltViewModel()
        val statePlayers: PlayersUiModel? by heroViewModel.playersUiModel.collectAsState()
        SearchPlayers(
            statePlayers = statePlayers,
            switchShowVillains = heroViewModel::showCompletedTasks)
    }
    composable(BuilderSectionsTabs.OREO.route) {

        LaunchedEffect(onboardingComplete) {
            if (!onboardingComplete.value) {
                navController.navigate(MainRoutes.ONBOARDING_ROUTE)
            }
        }
        if (onboardingComplete.value)
        { // Avoid glitch when showing onboarding
            Oreo(
                heroViewModel = hiltViewModel(),
                modifier = modifier
            )
        }
    }
    composable(BuilderSectionsTabs.CART.route) {
        Cart(modifier)
    }

    /*
    composable(BuilderSectionsTabs.FEATURED.route) { from ->
        OreoGrid(
            courses = courses,
            selectCourse = { id -> onPlayerSelected(id, from) },
            modifier = modifier
        )
    }
     */
}

@Composable
fun CoursesAppBar() {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.height(80.dp)
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.ic_lockup_white),
            contentDescription = null
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { /* todo */ }
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(R.string.label_profile)
            )
        }
    }
}

enum class BuilderSectionsTabs(
    @StringRes val title: Int,
    //@DrawableRes val icon: Int,
    val icon: ImageVector,
    val route: String
) {
    //MY_COURSES(R.string.my_courses, R.drawable.ic_grain, BuilderSections.MY_COURSES_ROUTE),
    //FEATURED(R.string.featured, Icons.Outlined.Search, BuilderSections.SEARCH_ROUTE),
    SEARCH(R.string.search, Icons.Outlined.List, BuilderSections.SEARCH_ROUTE),
    OREO(R.string.heroes, Icons.Outlined.Person, BuilderSections.OREO_ROUTE),
    CART(R.string.cart, Icons.Outlined.ShoppingCart, BuilderSections.CART_ROUTE)
}

/**
 * Destinations used in the ([OwlApp]).
 */
private object BuilderSections {
    const val FEATURED_ROUTE = "builder/featured"
    //const val MY_COURSES_ROUTE = "builder/my"
    const val SEARCH_ROUTE = "builder/search"
    const val OREO_ROUTE = "builder/oreo"
    const val CART_ROUTE = "builder/cart"
}
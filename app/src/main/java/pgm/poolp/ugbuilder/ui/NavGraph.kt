package pgm.poolp.ugbuilder.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import pgm.poolp.ugbuilder.data.PlayerCartCrossRef
import pgm.poolp.ugbuilder.ui.MainRoutes.PLAYER_DETAIL_ID_KEY
import pgm.poolp.ugbuilder.ui.onboarding.Onboarding
import pgm.poolp.ugbuilder.ui.player.PlayerDetails
import pgm.poolp.ugbuilder.ui.screens.BuilderSectionsTabs
import pgm.poolp.ugbuilder.ui.screens.buildSections
import pgm.poolp.ugbuilder.viewmodels.CartViewModel
import pgm.poolp.ugbuilder.viewmodels.HeroViewModel

/**
 * Destinations used in the ([OwlApp]).
 */
object MainRoutes {
    const val ONBOARDING_ROUTE = "onboarding"
    const val OREO_ROUTE = "oreo"
    const val PLAYER_DETAIL_ROUTE = "player"
    const val PLAYER_DETAIL_ID_KEY = "playerId"
}

@Composable
fun BuilderNavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainRoutes.OREO_ROUTE,
    showOnboardingInitially: Boolean = true
) {
    // Onboarding could be read from shared preferences.
    val onboardingComplete = remember(showOnboardingInitially) {
        mutableStateOf(!showOnboardingInitially)
    }

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainRoutes.ONBOARDING_ROUTE) {
            // Intercept back in Onboarding: make it finish the activity
            BackHandler {
                finishActivity()
            }

            Onboarding(
                onboardingComplete = {
                    // Set the flag so that onboarding is not shown next time.
                    onboardingComplete.value = true
                    actions.onboardingComplete()
                }
            )
        }
        navigation(
            route = MainRoutes.OREO_ROUTE,
            startDestination = BuilderSectionsTabs.OREO.route
        ) {
            buildSections(
                onPlayerSelected = actions.openPlayerDetail,
                onboardingComplete = onboardingComplete,
                navController = navController,
                modifier = modifier
            )
        }
        composable(
            "${MainRoutes.PLAYER_DETAIL_ROUTE}/{$PLAYER_DETAIL_ID_KEY}",
            arguments = listOf(
                navArgument(PLAYER_DETAIL_ID_KEY) { type = NavType.StringType }
            )
        ) { backStackEntry: NavBackStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val currentCourseId = arguments.getString(PLAYER_DETAIL_ID_KEY)
            if (currentCourseId != null) {

                val playerViewModel: HeroViewModel = hiltViewModel()
                val cartViewModel: CartViewModel = hiltViewModel()
                PlayerDetails(
                    playerId = currentCourseId,
                    playerViewModel = playerViewModel,
                    cartViewModel = cartViewModel,
                    addToCart = { playerId ->
                        //actions.relatedCourse(newCourseId, backStackEntry)
                        cartViewModel.insert(PlayerCartCrossRef(playerId,"tmnt"))
                    },
                    upPress = { actions.upPress(backStackEntry) }
                )
            }
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val onboardingComplete: () -> Unit = {
        navController.popBackStack()
    }

    // Used from COURSES_ROUTE
    val openPlayerDetail = { newPlayerId: String, from: NavBackStackEntry ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainRoutes.PLAYER_DETAIL_ROUTE}/$newPlayerId")
        }
    }

    // Used from COURSE_DETAIL_ROUTE
    val relatedCourse = { newCourseId: Long, from: NavBackStackEntry ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainRoutes.PLAYER_DETAIL_ROUTE}/$newCourseId")
        }
    }

    // Used from COURSE_DETAIL_ROUTE
    val upPress: (from: NavBackStackEntry) -> Unit = { from ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigateUp()
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

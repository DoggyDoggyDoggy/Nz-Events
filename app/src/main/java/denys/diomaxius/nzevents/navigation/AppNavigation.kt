package denys.diomaxius.nzevents.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.nzevents.ui.screen.event.EventDetailsScreen
import denys.diomaxius.nzevents.ui.screen.home.HomeScreen

val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not initialized")
}

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController()
) {
    CompositionLocalProvider(LocalNavController provides navHostController) {
        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }

            composable(Screen.Event.route) {
                val eventId = it.arguments?.getString("eventId")
                EventDetailsScreen(eventId = eventId ?: "")
            }
        }
    }
}
package denys.diomaxius.nzevents.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Event : Screen("event/{eventId}") {
        fun createRoute(eventId: String): String = "event/$eventId"
    }
}
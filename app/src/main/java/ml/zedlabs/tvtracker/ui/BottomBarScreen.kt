package ml.zedlabs.tvtracker.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {

    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Search: BottomBarScreen(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )

    object List: BottomBarScreen(
        route = "list",
        title = "List",
        icon = Icons.Default.List
    )

    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

}
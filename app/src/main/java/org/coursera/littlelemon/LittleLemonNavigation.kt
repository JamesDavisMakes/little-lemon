package org.coursera.littlelemon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.coursera.littlelemon.data.LittleLemonDatabase
import org.coursera.littlelemon.feature.home.HomeScreen
import org.coursera.littlelemon.feature.onboarding.OnboardingScreen
import org.coursera.littlelemon.feature.profile.ProfileScreen
import org.coursera.littlelemon.feature.shared.Home
import org.coursera.littlelemon.feature.shared.Onboarding
import org.coursera.littlelemon.feature.shared.Profile
import org.coursera.littlelemon.feature.shared.User
import org.coursera.littlelemon.feature.shared.deleteUser
import org.coursera.littlelemon.feature.shared.fetchUser
import org.coursera.littlelemon.feature.shared.saveUser


@Composable
fun LittleLemonNavigation(
    padding: PaddingValues = PaddingValues(),
    database: LittleLemonDatabase
) {
    val menuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    val navController = rememberNavController()
    val context = LocalContext.current

    var user: User? by remember {
        mutableStateOf(context.fetchUser())
    }

    val startRoute = if (user == null) {
        Onboarding.route
    } else {
        Home.route
    }

    NavHost(navController = navController, startDestination = startRoute, modifier = Modifier.padding(padding)) {
        composable(Onboarding.route) {
            OnboardingScreen(onUserUpdated = { firstName, lastName, email ->
                user = context.saveUser(firstName, lastName, email)  // We save the user in shared prefs for future app launches
                navController.navigate(Home.route, navOptions {
                    popUpTo(Onboarding.route) {
                        inclusive = true
                    }
                })
            })
        }
        composable(Home.route) {
            HomeScreen(menuItems, onProfileOpen = {
                navController.navigate(Profile.route)
            })
        }
        composable(Profile.route) {
            user?.apply {
                ProfileScreen(this, onLogOut = {
                    context.deleteUser()
                    user = null
                    navController.navigate(Onboarding.route)
                })
            }
        }
    }
}

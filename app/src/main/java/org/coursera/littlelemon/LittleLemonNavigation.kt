package org.coursera.littlelemon

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.coursera.littlelemon.data.LittleLemonDatabase
import org.coursera.littlelemon.feature.home.HomeScreen
import org.coursera.littlelemon.feature.onboarding.OnboardingScreen
import org.coursera.littlelemon.feature.profile.ProfileScreen
import org.coursera.littlelemon.feature.shared.Home
import org.coursera.littlelemon.feature.shared.LittleLemonAppBar
import org.coursera.littlelemon.feature.shared.Onboarding
import org.coursera.littlelemon.feature.shared.Profile
import org.coursera.littlelemon.feature.shared.User
import org.coursera.littlelemon.feature.shared.deleteUser
import org.coursera.littlelemon.feature.shared.fetchUser
import org.coursera.littlelemon.feature.shared.saveUser
import org.coursera.littlelemon.ui.theme.LittleLemonTheme


@Composable
fun LittleLemonNavigation(database: LittleLemonDatabase) {
    val navController = rememberNavController()
    val context = LocalContext.current
    var showBackButton by rememberSaveable { mutableStateOf(false) }
    var showProfileButton by rememberSaveable { mutableStateOf(false) }
    var user: User? by remember {
        mutableStateOf(context.fetchUser())
    }

    val startRoute = if (user == null) {
        Onboarding.route
    } else {
        Home.route
    }

    Scaffold(topBar = { LittleLemonAppBar(showBackButton, showProfileButton,
                                          onProfileClicked = { navController.navigate(Profile.route) },
                                          onBackClicked = { navController.popBackStack() })
    }) { innerPadding ->
        NavHost(navController = navController, startDestination = startRoute, modifier = Modifier.padding(innerPadding)) {
            composable(Onboarding.route) {
                OnboardingScreen(onUserUpdated = { firstName, lastName, email ->
                    user = context.saveUser(firstName, lastName, email)  // We save the user in shared prefs for future app launches
                    navController.navigate(Home.route, navOptions {
                        popUpTo(Onboarding.route) {
                            inclusive = true
                        }
                    })
                })
                showBackButton = Onboarding.showBackButton
                showProfileButton = Onboarding.showProfileButton
            }
            composable(Home.route) {
                HomeScreen(database)
                showBackButton = Home.showBackButton
                showProfileButton = Home.showProfileButton
            }
            composable(Profile.route) {
                user?.apply {
                    ProfileScreen(this, onLogOut = {
                        context.deleteUser()
                        user = null
                        navController.navigate(Onboarding.route)
                    })
                }
                showBackButton = Profile.showBackButton
                showProfileButton = Profile.showProfileButton
            }
        }
    }
}

@Preview
@Composable
private fun LittleLemonNavigationPreview() {
    LittleLemonTheme {
        LittleLemonNavigation(database = LittleLemonDatabase.getDatabase(LocalContext.current))
    }
}

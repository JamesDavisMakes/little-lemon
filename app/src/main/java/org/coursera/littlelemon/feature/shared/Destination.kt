package org.coursera.littlelemon.feature.shared

interface Destination {
    val route: String
}

object Onboarding : Destination {
    override val route: String = "Onboarding"
}

object Home : Destination {
    override val route: String = "Home"
}

object Profile : Destination {
    override val route: String = "Profile"
}

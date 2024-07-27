package org.coursera.littlelemon.feature.shared

interface Destination {
    val route: String
    val showBackButton: Boolean
    val showProfileButton: Boolean
}

object Onboarding : Destination {
    override val route: String = "Onboarding"
    override val showBackButton: Boolean = false
    override val showProfileButton: Boolean = false
}

object Home : Destination {
    override val route: String = "Home"
    override val showBackButton: Boolean = false
    override val showProfileButton: Boolean = true
}

object Profile : Destination {
    override val route: String = "Profile"
    override val showBackButton: Boolean = true
    override val showProfileButton: Boolean = false
}

package com.ndege.cheza.ndegecrunch.presentation.views.navigation

sealed class NavigationRoutes(val routeName: String) {
    data object DecisionScreen: NavigationRoutes("decision_screen")
    data object GameScreen : NavigationRoutes("game_screen")
    data object ProfileScreen : NavigationRoutes("profile_screen")
    data object DepositCash : NavigationRoutes("deposit_cash")
    data object WithdrawCash : NavigationRoutes("withdraw_cash")
    data object MainGame: NavigationRoutes("main_screen")
    data object PrivacyPolicy: NavigationRoutes("privacy_policy")
}
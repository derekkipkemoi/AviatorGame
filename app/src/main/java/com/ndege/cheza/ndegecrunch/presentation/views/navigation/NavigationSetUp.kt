package com.ndege.cheza.ndegecrunch.presentation.views.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ndege.cheza.ndegecrunch.presentation.componentsCollections.Game
import com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.Main
import com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.DecisionPoint
import com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.cash.depositCash.DepositCash
import com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.cash.withdrawCash.WithdrawCash
import com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.profile.PrivacyPolicyScreen
import com.ndege.cheza.ndegecrunch.presentation.views.componentsCollections.screens.profile.ProfileScreen

@Composable
fun NavigationSetUp(
navHostController: NavHostController
){
    NavHost(navController = navHostController,
        startDestination = NavigationRoutes.DecisionScreen.routeName) {
        composable(route = NavigationRoutes.DecisionScreen.routeName){
            DecisionPoint(navController= navHostController)
        }
        composable(route = NavigationRoutes.GameScreen.routeName){
            Game()
        }
        composable(route = NavigationRoutes.MainGame.routeName){
            Main(navController = navHostController)
        }
        composable(route = NavigationRoutes.ProfileScreen.routeName){
            ProfileScreen(navHostController)
        }
        composable(route = NavigationRoutes.DepositCash.routeName){
            DepositCash(navHostController)
        }
        composable(route = NavigationRoutes.WithdrawCash.routeName){
            WithdrawCash(navHostController)
        }
        composable(route = NavigationRoutes.PrivacyPolicy.routeName){
            PrivacyPolicyScreen(navHostController)
        }
    }

}
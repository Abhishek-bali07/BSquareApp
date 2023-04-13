package com.bsquare.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bsquare.app.navigation.screen_transitions.AppScreenTransitions
import com.bsquare.app.presentation.ui.screens.intro.SplashScreen
import com.bsquare.app.presentation.ui.screens.login.LoginScreen
import com.bsquare.app.presentation.ui.view_models.BaseViewModel
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.utils.helper.NavigationIntent
import kotlinx.coroutines.channels.Channel

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    navigationChannel: Channel<NavigationIntent>,
    paddingValues: PaddingValues,
    baseViewModel: BaseViewModel
){
    navHostController.NavEffects(navigationChannel)
    AppNavHost(
            navController = navHostController,
            startDestination = Destination.SplashScreen,
            modifier = Modifier.padding(paddingValues),
            enterTransition = AppScreenTransitions.ScreenEnterTransition,
            popEnterTransition = AppScreenTransitions.ScreenPopEnterTransition,
            exitTransition = AppScreenTransitions.ScreenExitTransition,
            popExitTransition = AppScreenTransitions.ScreenPopExitTransition,
        ){
        composable(destination = Destination.SplashScreen){
            SplashScreen()
        }
        composable(destination =   Destination.LoginScreen ){
            LoginScreen()
        }
    }
}
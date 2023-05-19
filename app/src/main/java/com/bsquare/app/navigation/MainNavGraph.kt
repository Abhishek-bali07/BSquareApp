package com.bsquare.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bsquare.app.navigation.screen_transitions.AppScreenTransitions
import com.bsquare.app.presentation.ui.screens.Lead.*
import com.bsquare.app.presentation.ui.screens.dashboard.DashboardScreen
import com.bsquare.app.presentation.ui.screens.followup.AddTaskScreen
import com.bsquare.app.presentation.ui.screens.followup.FollowupScreen
import com.bsquare.app.presentation.ui.screens.intro.SplashScreen
import com.bsquare.app.presentation.ui.screens.login.LoginScreen
import com.bsquare.app.presentation.ui.view_models.BaseViewModel
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.entities.Follow
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
            startDestination = Destination.AddActivityScreen,
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
        composable(destination = Destination.DashboardScreen){
            DashboardScreen()
        }
        composable(destination = Destination.LeadScreen){
            LeadScreen(baseViewModel = baseViewModel)
        }
        composable(destination = Destination.CompanyDetailScreen){
            CompanyDetailScreen(baseViewModel = baseViewModel)
        }

        composable(destination = Destination.FilterScreen){
            FilterScreen(baseViewModel = baseViewModel)
        }

        composable(destination = Destination.AddleadScreen){
            AddLeadScreen()
        }

        composable(destination = Destination.FollowupScreen){
            FollowupScreen()
        }

        composable(destination = Destination.AddTaskScreen){
            AddTaskScreen(baseViewModel = baseViewModel)
        }

        composable(destination = Destination.AddActivityScreen){
            AddActivityScreen()
        }

    }
}
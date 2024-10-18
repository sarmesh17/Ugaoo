package com.sharkdroid.ugaoo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sharkdroid.ugaoo.presentation.ui.addplantform.AddPlantForm
import com.sharkdroid.ugaoo.presentation.ui.createaccountscreen.CreateAccount
import com.sharkdroid.ugaoo.presentation.ui.homescreen.UgaooHomeScreen
import com.sharkdroid.ugaoo.presentation.ui.login.LoginScreen
import com.sharkdroid.ugaoo.presentation.viewmodels.AddPlantViewModel
import com.sharkdroid.ugaoo.presentation.viewmodels.LoginScreenViewModel
import com.sharkdroid.ugaoo.presentation.viewmodels.SignUpScreenViewModel

@Composable
fun UgaooNavigationSystem(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LoginScreen ) {

        composable<Routes.LoginScreen> {
            val loginScreenViewModel:LoginScreenViewModel= hiltViewModel()
            LoginScreen(navController,loginScreenViewModel)
        }

        composable<Routes.SignUpScreen> {
            val siginUpScreenViewModel:SignUpScreenViewModel= hiltViewModel()
            CreateAccount(navController,siginUpScreenViewModel)
        }

        composable<Routes.HomeScreen> {
            UgaooHomeScreen(navController)
        }

        composable<Routes.AddPlantScreen> {

            val viewModel:AddPlantViewModel= hiltViewModel()
            AddPlantForm(navController,viewModel)

        }

    }



}
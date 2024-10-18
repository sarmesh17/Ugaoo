package com.sharkdroid.ugaoo.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object LoginScreen

    @Serializable
    data object SignUpScreen

    @Serializable
    data object  HomeScreen

    @Serializable
    data object AddPlantScreen

    @Serializable
    data object SplashScreen
    


}
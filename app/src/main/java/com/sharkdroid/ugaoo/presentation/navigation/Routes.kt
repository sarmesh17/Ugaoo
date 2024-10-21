package com.sharkdroid.ugaoo.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object LoginScreen:Routes()

    @Serializable
    data object SignUpScreen:Routes()

    @Serializable
    data object  HomeScreen:Routes()

    @Serializable
    data object AddPlantScreen

    @Serializable
    data object SplashScreen:Routes()
    


}
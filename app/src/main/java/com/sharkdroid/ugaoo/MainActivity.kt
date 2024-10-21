package com.sharkdroid.ugaoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.FirebaseApp
import com.sharkdroid.ugaoo.presentation.navigation.UgaooNavigationSystem
import com.sharkdroid.ugaoo.presentation.ui.spalshscreen.SplashScreen
import com.sharkdroid.ugaoo.ui.theme.UgaooTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            UgaooTheme {

                val isSplashScreenVisible = remember {
                    mutableStateOf(true)
                }

                LaunchedEffect(key1 = true) {
                    // Simulate a delay for loading or additional splash logic
                    delay(2000)
                    isSplashScreenVisible.value = false
                }

                if (isSplashScreenVisible.value){

                    SplashScreen()

                }else{

                    val startDestination= mainViewModel.startDestination
                    UgaooNavigationSystem(startDestination)
                }


            }
        }
    }
}


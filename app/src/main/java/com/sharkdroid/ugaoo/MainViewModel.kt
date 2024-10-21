package com.sharkdroid.ugaoo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharkdroid.ugaoo.domain.usecases.AllUseCases
import com.sharkdroid.ugaoo.presentation.navigation.Routes
import com.sharkdroid.ugaoo.presentation.ui.spalshscreen.SplashScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val allUseCases: AllUseCases
) : ViewModel() {

    private var _startDestination: MutableStateFlow<Routes> = MutableStateFlow(Routes.SplashScreen)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            // Delay for the splash screen duration (2000ms)
            delay(2000)

            // Check login status after splash screen delay
            allUseCases.readLoginEntry().onEach { loginEntryStatus ->
                _startDestination.value = if (loginEntryStatus) {
                    Routes.HomeScreen
                } else {
                    Routes.LoginScreen
                }
            }.launchIn(viewModelScope)
        }
    }
}

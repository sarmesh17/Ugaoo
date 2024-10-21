package com.sharkdroid.ugaoo

import androidx.lifecycle.ViewModel
import com.sharkdroid.ugaoo.domain.usecases.AllUseCases
import com.sharkdroid.ugaoo.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val allUseCases: AllUseCases
) : ViewModel() {


    private var _startDestination: Routes = Routes.LoginScreen
    val startDestination = _startDestination


    init {


        _startDestination = Routes.LoginScreen

        allUseCases.readLoginEntry().onEach { loginEntryStatus ->

            if (loginEntryStatus) {

                _startDestination = Routes.HomeScreen
            }


        }
    }

}








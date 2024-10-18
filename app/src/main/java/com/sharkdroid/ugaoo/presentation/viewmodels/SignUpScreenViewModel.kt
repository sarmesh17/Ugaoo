package com.sharkdroid.ugaoo.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sharkdroid.ugaoo.domain.model.UserAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : ViewModel() {

    private var _isSignedUp = MutableStateFlow(false)
    val isSignedUp = _isSignedUp.asStateFlow()

    private var _errorMessage by mutableStateOf("")
    val errorMessage: String get() = _errorMessage

    fun signUpUser( email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = firebaseAuth.currentUser?.uid ?: return@addOnCompleteListener
                val user = UserAuth(email, password)

                firebaseDatabase.getReference("users").child(userId).setValue(user).addOnCompleteListener { databaseTask ->
                    if (databaseTask.isSuccessful) {
                        Log.d("SignUp", "Sign up successful")
                        _isSignedUp.value = true
                    } else {
                        _errorMessage = databaseTask.exception?.message ?: "Unknown error"
                        Log.d("SignUp", "Database error: $_errorMessage")
                    }
                }
            } else {
                _errorMessage = task.exception?.message ?: "Unknown error"
                Log.d("SignUp", "Sign up error: $_errorMessage")
            }
        }
    }
}

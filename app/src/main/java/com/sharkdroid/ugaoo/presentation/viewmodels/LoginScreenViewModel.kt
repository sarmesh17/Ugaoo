package com.sharkdroid.ugaoo.presentation.viewmodels

import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.sharkdroid.ugaoo.common.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.sharkdroid.ugaoo.domain.model.UserAuth

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val databaseReference: FirebaseDatabase,
    private val oneTapClient: SignInClient
) : ViewModel() {

    private val userLiveData = MutableLiveData<FirebaseUser?>()
    val intentSenderLiveData = MutableLiveData<IntentSender?>()
    val loginSuccessLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String?>()

    fun signInUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = firebaseAuth.currentUser?.uid ?: return@addOnCompleteListener
                // Fetch user data from Firebase Realtime Database
                databaseReference.getReference("users").child(userId).get()
                    .addOnCompleteListener { dataTask ->
                        if (dataTask.isSuccessful) {
                            val user = dataTask.result.getValue(UserAuth::class.java)
                            if (user != null && user.email == email) {
                                onResult(true, null)
                            } else {
                                onResult(false, "User data does not match.")
                            }
                        } else {
                            onResult(false, dataTask.exception?.message ?: "Data retrieval failed.")
                        }
                    }
            } else {
                onResult(false, task.exception?.message ?: "Login failed.")
            }
        }
    }

    // Google sign-in
    fun startSignIn() {
        Log.d("SignIn", "Starting sign-in process")
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(Constant.WEB_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener { result ->
                Log.d("SignIn", "Sign-in request successful")
                intentSenderLiveData.postValue(result.pendingIntent.intentSender)
            }
            .addOnFailureListener { e ->
                Log.d("SignIn", "Sign-in request failed: ${e.localizedMessage}")
                errorLiveData.postValue(e.localizedMessage)
            }
    }

    fun handleSignInResult(data: Intent?) {
        Log.d("SignInResult", "Handling sign-in result")
        viewModelScope.launch {
            try {
                val credential = oneTapClient.getSignInCredentialFromIntent(data)
                val idToken = credential.googleIdToken

                if (idToken != null) {
                    Log.d("SignInResult", "ID token retrieved")
                    val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                    firebaseAuth.signInWithCredential(firebaseCredential).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            userLiveData.postValue(firebaseAuth.currentUser)
                            loginSuccessLiveData.postValue(true)
                        } else {
                            Log.d("SignInResult", "Firebase sign-in failed: ${task.exception?.localizedMessage}")
                            errorLiveData.postValue(task.exception?.localizedMessage)
                            loginSuccessLiveData.postValue(false)
                        }
                    }
                } else {
                    Log.d("SignInResult", "No ID token found")
                    errorLiveData.postValue("No ID token!")
                }
            } catch (e: ApiException) {
                Log.d("SignInResult", "Error retrieving credentials: ${e.localizedMessage}")
                errorLiveData.postValue(e.localizedMessage)
            }
        }
    }
}

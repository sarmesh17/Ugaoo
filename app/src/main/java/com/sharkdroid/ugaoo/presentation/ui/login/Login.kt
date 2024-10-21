package com.sharkdroid.ugaoo.presentation.ui.login

import android.app.Activity.RESULT_OK
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.ugaoo.R
import com.sharkdroid.ugaoo.domain.usecases.AllUseCases
import com.sharkdroid.ugaoo.presentation.common.UgaooButton
import com.sharkdroid.ugaoo.presentation.navigation.Routes
import com.sharkdroid.ugaoo.presentation.viewmodels.LoginScreenViewModel


@Composable
fun LoginScreen(navController: NavHostController, loginScreenViewModel: LoginScreenViewModel) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember { mutableStateOf(false) }




    val context = LocalContext.current

    val intentSender by loginScreenViewModel.intentSenderLiveData.observeAsState()
    val loginSuccess by loginScreenViewModel.loginSuccessLiveData.observeAsState()
    val error by loginScreenViewModel.errorLiveData.observeAsState()

    LaunchedEffect(loginSuccess) {

        if(loginSuccess == true){

            navController.navigate(Routes.HomeScreen)
        }

    }



    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                loginScreenViewModel.handleSignInResult(result.data)
            } else {
                Log.e("SignInFailure", "Sign-in failed with resultCode: ${result.resultCode}")
                result.data?.let {
                    val errorMessage = it.getStringExtra("error_message")
                    Log.e("SignInFailure", "Error Message: $errorMessage")
                    // Handle failure
                }

            }
        }

    LaunchedEffect(intentSender) {
        intentSender?.let {
            launcher.launch(IntentSenderRequest.Builder(it).build())
        }
    }

    LaunchedEffect(loginSuccess) {
        if (loginSuccess == true) {
            navController.navigate(Routes.HomeScreen)
        }
    }





    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painter = painterResource(id = R.drawable.plant_cover), contentDescription = null)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)

        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome",
                    fontSize = 36.sp,
                    color = colorResource(id = R.color.Deep_Teal),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))
                // email
                OutlinedTextField(value = email, onValueChange = {
                    email = it
                },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Email")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
                // password
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Password")
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            painterResource(id = R.drawable.visible_icon) // Replace with your visible icon
                        else
                            painterResource(id = R.drawable.no_visibility_icon) // Replace with your invisible icon

                        IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            Icon(
                                painter = image,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

                    Text(text = "Forgot Password ?", color = colorResource(id = R.color.Deep_Teal))
                }

                // Sigin Button
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {

                    UgaooButton(value = "Sign in", onClick = {
                        loginScreenViewModel.signInUser(email, password) { isSuccess, error ->
                            if (isSuccess) {
                                Log.d("Sign btn", "Login successful")
                                navController.navigate(Routes.HomeScreen)
                            } else {
                                Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
                                Log.d("Sign btn", "Login failed = $error")

                            }

                        }
                    })
                }

                Spacer(modifier = Modifier.height(16.dp))


                Text(text = "or continue with", fontSize = 16.sp)


                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {

                    //  Google Btn
                    Box(
                        modifier = Modifier
                            .size(180.dp, 50.dp)
                            .border(
                                border = BorderStroke(0.5.dp, color = Color.Gray),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                loginScreenViewModel.startSignIn()


                            }
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 18.dp)
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.google),
                                contentDescription = null, modifier = Modifier.size(40.dp)
                            )

                            Text(text = "Google", modifier = Modifier.padding(end = 38.dp))

                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    //  Facebook Btn
                    Box(
                        modifier = Modifier
                            .size(180.dp, 50.dp)
                            .border(
                                border = BorderStroke(0.5.dp, color = Color.Gray),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {

                            }
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 18.dp)
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.facebook),
                                contentDescription = null, modifier = Modifier.size(34.dp)
                            )

                            Text(text = "Google", modifier = Modifier.padding(end = 38.dp))

                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                Row {

                    Text(text = "Not a member?", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Join Now",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.Deep_Teal),
                        fontWeight = FontWeight.Bold, modifier = Modifier.clickable {
                            navController.navigate(Routes.SignUpScreen)
                        }
                    )
                }
            }
        }
    }

}
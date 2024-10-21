package com.sharkdroid.ugaoo.presentation.ui.spalshscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.ugaoo.R
import com.sharkdroid.ugaoo.presentation.common.UgaooButton
import com.sharkdroid.ugaoo.presentation.navigation.UgaooNavigationSystem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Preview(showSystemUi = true)
fun SplashScreen() {

    val coroutineScope= rememberCoroutineScope()


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.lotus_splash),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
        ) {

            Column {


                Text(
                    text = "Ugaoo",
                    fontSize = 66.sp,
                    color = Color.White, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Find your favorite",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 5.dp)
                )

                Text(
                    text = "plants on our shop",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontStyle = FontStyle.Italic,

                    fontWeight = FontWeight.Bold
                )

            }
        }



    }



}
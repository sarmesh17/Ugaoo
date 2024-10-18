package com.sharkdroid.ugaoo.presentation.common

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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.ugaoo.R

@Composable
@Preview(showBackground = true)
fun ProductCard(){

   Card(modifier = Modifier.size(167.dp,228.dp)) {
       Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
           Column {
            Image(painter = painterResource(id = R.drawable.plant_img_1), contentDescription = null, modifier = Modifier.size(140.dp,150.dp))

               Spacer(modifier = Modifier.height(4.dp))

               Column(modifier = Modifier
                   .fillMaxWidth()
                   .padding(start = 16.dp)) {
                   //plant-name
                   Text(text = "Monstera", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)

                   Text(text = "Indoor", color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
               }

               Row (modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.End){

                   Text(text = "$200", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = colorResource(
                       id = R.color.Deep_Teal
                   ))
               }
           }
       }
   }

}
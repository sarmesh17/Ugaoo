package com.sharkdroid.ugaoo.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharkdroid.ugaoo.R

@Composable
fun UgaooButton(value: String, onClick: () -> Unit, modifier:Modifier= Modifier) {

    Box(
        modifier = Modifier
            .height(45.dp).fillMaxWidth()
            .background(
                color = colorResource(id = R.color.Deep_Teal),
                shape = RoundedCornerShape(6.dp)
            )
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {

        Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)

    }

}


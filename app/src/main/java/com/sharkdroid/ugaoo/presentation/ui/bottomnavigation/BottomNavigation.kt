package com.sharkdroid.ugaoo.presentation.ui.bottomnavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sharkdroid.ugaoo.R
import com.sharkdroid.ugaoo.presentation.navigation.Routes

@Composable
fun BottomNavigation(
    navHostController: NavHostController
) {

    // Remember the currently selected icon's index
    var selectedIcon by remember { mutableStateOf(0) }

    Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
        Icon(
            painter = painterResource(id = R.drawable.bottom_design),
            contentDescription = null,
            tint = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .height(165.dp)
        )

        Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.Center) {
            if (selectedIcon ==  4) {
                Image(
                    painter = painterResource(id = R.drawable.potted_plant),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(66.dp)
                        .clickable {
                            selectedIcon = 4
                        }, colorFilter = ColorFilter.tint(color = colorResource(id = R.color.Deep_Teal))
                )
            }else{

                Image(
                    painter = painterResource(id = R.drawable.potted_plant_unfilled),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(66.dp)
                        .clickable {
                            selectedIcon = 4
                            navHostController.navigate(Routes.AddPlantScreen)

                        }
                )

            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 70.dp)
                .wrapContentSize()
        ) {
            IconButton(onClick = { selectedIcon = 0 }) {
                Icon(
                    painter = painterResource(
                        id = if (selectedIcon == 0) R.drawable.home_icon else R.drawable.home_unfilled
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(31.dp, 28.dp), tint = if (selectedIcon == 0) {colorResource(id = R.color.Deep_Teal)} else Color.Black
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = { selectedIcon = 1 }) {
                Icon(
                    painter = painterResource(
                        id = if (selectedIcon == 1) R.drawable.favourite else R.drawable.heart_unfilled
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(31.dp, 28.dp)
                    , tint = if (selectedIcon == 1) {colorResource(id = R.color.Deep_Teal)} else Color.Black
                )
            }

            Spacer(modifier = Modifier.width(130.dp))

            IconButton(onClick = { selectedIcon = 2 }) {
                Icon(
                    painter = painterResource(
                        id = if (selectedIcon == 2) R.drawable.cart else R.drawable.cart_unfilled
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(31.dp, 28.dp)
                    , tint = if (selectedIcon == 2) {colorResource(id = R.color.Deep_Teal)} else Color.Black
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = { selectedIcon = 3 }) {
                Icon(
                    painter = painterResource(
                        id = if (selectedIcon == 3) R.drawable.profile else R.drawable.profile_unfilled
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(31.dp, 28.dp)
                    , tint = if (selectedIcon == 3) {colorResource(id = R.color.Deep_Teal)} else Color.Black
                )
            }
        }
    }
}

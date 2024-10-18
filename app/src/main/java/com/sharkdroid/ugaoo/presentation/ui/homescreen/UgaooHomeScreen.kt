package com.sharkdroid.ugaoo.presentation.ui.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.ugaoo.R
import com.sharkdroid.ugaoo.presentation.ui.bottomnavigation.BottomNavigation
import com.sharkdroid.ugaoo.presentation.ui.searchbar.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UgaooHomeScreen(navController: NavHostController) {

    val tab = listOf("Recommend", "Indoor", "Outdoor")

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "User Icon", modifier = Modifier
                            .size(34.dp)
                            .clip(
                                RoundedCornerShape(12.dp)
                            )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.navigation),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = colorResource(
                                id = R.color.Deep_Teal
                            )
                        )
                    }
                }
            )
        }, bottomBar ={BottomNavigation(navController)}
    ) {
        Column(modifier = Modifier.padding(it)) {

            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                Text(
                    text = "Let's Find \nYour Plants!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                SearchBar(query = "", onQueryChanged = {}) {

                }

                Spacer(modifier = Modifier.height(30.dp))

                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    contentColor = colorResource(id = R.color.Deep_Teal),
                    indicator = { tabPositions ->
                        SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                            color = colorResource(id = R.color.Deep_Teal) // Change this to your desired color
                        )
                    }
                ) {

                    tab.forEachIndexed { index, title ->

                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            selectedContentColor = colorResource(
                                id = R.color.Deep_Teal
                            ), unselectedContentColor = Color.Black
                        ) {

                            Text(text = title, fontSize = 18.sp, modifier = Modifier.padding(bottom = 12.dp))

                        }
                    }
                }

                // Showing content based on selected tab
                when (selectedTabIndex) {
                    0 -> {



                    }

                    1 -> Text("Indoor", modifier = Modifier.padding(16.dp))
                    2 -> Text("Outdoor", modifier = Modifier.padding(16.dp))
                }

            }
        }
    }
}
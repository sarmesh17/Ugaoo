package com.sharkdroid.ugaoo.presentation.ui.searchbar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 12.dp
    ) {


        Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text field

        TextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = { Text(text = "Search plants...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardActions = KeyboardActions(onSearch = { onSearch() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black // Color of the search icon
                )

            },
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.White, unfocusedIndicatorColor = Color.White)
        )
    }
}





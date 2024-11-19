@file:Suppress("UNUSED_EXPRESSION")

package com.example.expensetrackerapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp


@Composable
fun AppBar(title : String){
    val navigationIcon : (@Composable ()->Unit) ? =
if(title!= "Expense Tracker") {
    {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.Black,
                contentDescription = null)
        }

    }
}
    else{
        null
    }

    TopAppBar(
        title = {
            androidx.compose.material.Text(
                text = title,
                color = colorResource(id = R.color.white),
                modifier = androidx.compose.ui.Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        elevation  = 3.dp,
        backgroundColor = Color.Black,
        navigationIcon = navigationIcon
    )
}
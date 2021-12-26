package com.example.compose.jetchat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose.jetchat.R
import java.util.*


@Composable
fun JetchatAppBar(
    modifier: Modifier = Modifier,
) {

    TopAppBar(
        modifier = Modifier.padding(16.dp),
        backgroundColor = androidx.compose.ui.graphics.Color.Green,
        contentColor = androidx.compose.ui.graphics.Color.White,
        elevation = 10.dp,
        contentPadding = PaddingValues(10.dp),
    ) {
        Row {

            Icon(painter = painterResource(id = R.drawable.ic_jetchat),contentDescription = null)
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Vihaan is in the matrix")
        }
    }


    TopAppBar(
        title = {
            Text(text = "fuck")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp)
            .padding(start = 10.dp, end = 10.dp),
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_jetchat),
                contentDescription = null
            )
        },
        actions = {
            Icon(painterResource(id = R.drawable.ic_baseline_person_24), contentDescription = null)
        }
    )

    Row {
        Image(painter = painterResource(id = R.drawable.ic_jetchat),
            contentDescription = null,
            modifier = Modifier.padding(start = 16.dp))

    }
}


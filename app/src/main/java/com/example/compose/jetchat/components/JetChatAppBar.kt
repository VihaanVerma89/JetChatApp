package com.example.compose.jetchat.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetchat.R
import com.example.compose.jetchat.ui.theme.JetChatAppTheme
import com.example.compose.jetchat.ui.theme.elevatedSurface


@Composable
fun JetChatAppBar(
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = {},
    title: @Composable RowScope.() -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {

    val backgroundColor = MaterialTheme.colors.elevatedSurface(elevation = 3.dp)
    Column(
        Modifier.background(backgroundColor.copy(alpha = 0.95f))
    ) {

        TopAppBar(
            modifier = modifier,
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = MaterialTheme.colors.onSurface,
            actions = actions,
            title = {
                Row {
                    title()
                }
            },
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_jetchat),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(onClick = onNavIconPressed)
                        .padding(horizontal = 16.dp)
                )
            },
        )
        Divider()
    }
}

const val TAG = "JetChatAppBar"

@Preview
@Composable
fun PreviewJetChatAppBar(
) {
    JetChatAppTheme {
        JetChatAppBar(
            onNavIconPressed = {
                Log.d(TAG, "Nav icon clicked")
            },
            title = {
                Text("Preview!")
            }
        )
    }

}
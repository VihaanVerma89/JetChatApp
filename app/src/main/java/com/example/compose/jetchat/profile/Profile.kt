package com.example.compose.jetchat.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose.jetchat.R
import com.example.compose.jetchat.components.JetChatAppBar
import com.example.compose.jetchat.data.meProfile
import com.example.compose.jetchat.ui.theme.JetChatAppTheme
import com.google.accompanist.insets.statusBarsPadding


@Composable
fun ProfileScreen(
    userData: ProfileScreenState,
) {
    val scrollState = rememberScrollState()
    Column {
        JetChatAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            onNavIconPressed = {

            },
            title = {

            },
            actions = {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Image(
                        imageVector = Icons.Outlined.MoreVert,
                        modifier = Modifier
                            .clickable(onClick = { })
                            .padding(horizontal = 12.dp, vertical = 16.dp),
                        contentDescription = null)

                }
            },
        )
        BoxWithConstraints {
            Surface {
                Column {
                    ProfileHeader(scrollState = scrollState,
                        data = userData,
                        containerHeight = this@BoxWithConstraints.maxHeight)
                    UserInfoFields(userData = userData,
                        containerHeight = this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
fun ProfileProperty(
    label: String,
    value: String,
    isLink: Boolean = false,
) {
    Column(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(text = label,
                style = MaterialTheme.typography.caption)
        }

        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(text = value, style = style)
    }
}

@Composable
fun UserInfoFields(
    userData: ProfileScreenState,
    containerHeight: Dp,
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        NameAndPosition(userData = userData)

        ProfileProperty(stringResource(R.string.display_name), userData.displayName)

        ProfileProperty(stringResource(R.string.status), userData.status)

        ProfileProperty(stringResource(R.string.twitter), userData.twitter, isLink = true)

        userData.timeZone?.let {
            ProfileProperty(stringResource(R.string.timezone), userData.timeZone)
        }

    }
}

@Composable
fun ProfileHeader(
    scrollState: ScrollState,
    data: ProfileScreenState,
    containerHeight: Dp,
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    data.photo?.let {
        Image(
            modifier = Modifier
                .heightIn(max = containerHeight / 2)
                .fillMaxWidth()
                .padding(top = offsetDp),
            painter = painterResource(id = it),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }

}

@Composable
fun NameAndPosition(
    userData: ProfileScreenState,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Name(userData = userData)
        Position(userData = userData, modifier = Modifier.padding(bottom = 24.dp))
    }
}

@Composable
fun Name(
    userData: ProfileScreenState, modifier: Modifier = Modifier,
) {
    Text(text = userData.name,
        modifier = modifier,
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun Position(
    userData: ProfileScreenState, modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = userData.position,
            modifier = modifier,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview(
) {
    JetChatAppTheme {
        ProfileScreen(meProfile)
    }
}
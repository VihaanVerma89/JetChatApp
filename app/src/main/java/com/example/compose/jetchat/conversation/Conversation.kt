package com.example.compose.jetchat.conversation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetchat.R
import com.example.compose.jetchat.components.JetChatAppBar
import com.example.compose.jetchat.data.exampleUiState

@Preview
@Composable
fun PreviewUI(
) {
    ConversationContent(uiState = exampleUiState, {

    })
}

@Composable
fun ConversationContent(
    uiState: ConversationUiState,
    navigateToProfile: (String) -> Unit,
    modifer: Modifier = Modifier,
    onNavIconPressed: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize())
    {

        Column {
            Messages(messages = uiState.messages)
            UserInput(

            )
        }
        ChannelNameBar(
            channelName = uiState.channelName,
            channelMembers = uiState.channelMembers,
            onNavIconPressed = onNavIconPressed,
        )
    }
}

@Composable
fun ChannelNameBar(
    channelName: String,
    channelMembers: Int,
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = {

    },
) {

    JetChatAppBar(
        modifier = Modifier,
        onNavIconPressed = onNavIconPressed,
        title = {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = channelName,
                    style = MaterialTheme.typography.subtitle1)

                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = stringResource(id = R.string.members, channelMembers),
                        style = MaterialTheme.typography.caption)
                }
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Outlined.Search,
                modifier = Modifier
                    .clickable(onClick = { })
                    .padding(horizontal = 12.dp, vertical = 16.dp)
                    .height(24.dp),
                contentDescription = null
            )
            Icon(
                imageVector = Icons.Outlined.Info,
                modifier = Modifier
                    .clickable(onClick = { })
                    .padding(horizontal = 12.dp, vertical = 16.dp)
                    .height(24.dp),
                contentDescription = null
            )
        }
    )

}

@Composable
fun Messages(
    messages: List<Message>,

    ) {
    LazyColumn(reverseLayout = true)
    {
        for (index in messages.indices) {
            val content = messages[index]
            item {
                Message(onAuthorClick = {

                },
                    msg = content
                )
            }
        }
    }
}


@Composable
fun Message(
    onAuthorClick: (String) -> Unit,
    msg: Message,
) {

    Row {
        Image(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(42.dp)
                .border(3.dp, MaterialTheme.colors.surface, CircleShape)
                .clip(CircleShape),
            painter = painterResource(id = msg.authorImage),
            contentScale = ContentScale.Crop,
            contentDescription = null)
        AuthorAndTextMessage(msg = msg)
    }

}


@Composable
fun ClickableMessage(
    message: Message, authorClicked: (String) -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    val styledMessage = messageFormatter(text = message.content)

    ClickableText(
        text = styledMessage,
        style = MaterialTheme.typography.body1.copy(LocalContentColor.current),
        modifier = Modifier.padding(8.dp),
        onClick = {
            styledMessage
                .getStringAnnotations(start = it, end = it)
                .firstOrNull()
                ?.let { annotation ->
                    when (annotation.tag) {
                        SymbolAnnotationType.LINK.name -> uriHandler.openUri(annotation.item)
                        SymbolAnnotationType.PERSON.name -> authorClicked(annotation.item)
                        else -> Unit
                    }
                }
        })
}

@Composable
fun AuthorAndTextMessage(
    msg: Message,
) {
    Column {
        ChatItemBubble(message = msg)
    }
}

@Composable
fun ChatItemBubble(
    message: Message,
) {

    ClickableMessage(message = message, authorClicked = {})
}

@Composable
fun DayHeader(
    dayString: String,
) {
    Row {
        DayHeaderLine()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = dayString,
                style = MaterialTheme.typography.overline,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        DayHeaderLine()
    }
}

@Composable
fun RowScope.DayHeaderLine(
) {
    Divider(
        Modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
    )
}
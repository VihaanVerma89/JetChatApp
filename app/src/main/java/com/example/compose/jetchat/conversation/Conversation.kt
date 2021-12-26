package com.example.compose.jetchat.conversation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetchat.data.exampleUiState

@Preview
@Composable
fun PreviewUI(
) {
    ConversationContent(uiState = exampleUiState)
}

@Composable
fun ConversationContent(
    uiState: ConversationUiState,
) {
    DayHeader(dayString = "Today")
    Column {
        Messages(messages = uiState.messages)
    }
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
        Image(painter = painterResource(id = msg.authorImage), contentDescription = null)
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
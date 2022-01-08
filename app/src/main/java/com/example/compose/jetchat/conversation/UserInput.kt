package com.example.compose.jetchat.conversation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetchat.R


enum class InputSelector {
    NONE, MAP, DM, EMOJI, PHONE, PICTURE
}

@Preview
@Composable
fun PreviewUserInput(
) {
    UserInput()
}

@Composable
fun UserInput() {
    var textState by remember { mutableStateOf(TextFieldValue()) }

    Column {
        UserInputText(
            textFieldValue = textState,
            onTextChanged = {
                textState = it
            }
        )
        UserInputSelector()
    }
}

@Composable
fun UserInputSelector(
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InputSelectorButton(
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.Mood
        )

        InputSelectorButton(
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.AlternateEmail
        )
        InputSelectorButton(
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.InsertPhoto
        )
        InputSelectorButton(
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.Place
        )
        InputSelectorButton(
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.Duo
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(36.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.send),
                modifier = Modifier.padding(horizontal = 16.dp))
        }

    }

}

@Composable
fun InputSelectorButton(
    onClick: () -> Unit,
    icon: ImageVector,
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(12.dp)
                .size(20.dp)
        )
    }
}


@Composable
fun UserInputText(
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,

    ) {

    BasicTextField(
        value = textFieldValue,
        onValueChange = {
            onTextChanged(it)
        },
        maxLines = 1)

    val disableContentColor =
        MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
    if (textFieldValue.text.isEmpty()) {
        Text(
            text = stringResource(id = R.string.textfield_hint),
            modifier = Modifier
//                .align(Alignment.CenterEnd)
                .padding(start = 16.dp),
            style = MaterialTheme.typography.body1.copy(color = disableContentColor)
        )
    }

}
package com.example.compose.jetchat.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.compose.jetchat.MainViewModel
import com.example.compose.jetchat.ui.theme.JetChatAppTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ViewWindowInsetObserver

class ConversationFragment: Fragment() {

    private val activityViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val composeView = ComposeView(inflater.context).apply {

            // Create a ViewWindowInsetObserver using this view, and call start() to
            // start listening now. The WindowInsets instance is returned, allowing us to
            // provid it to AmbientWindowInsets in our content below.
            val windowInsets = ViewWindowInsetObserver(this)
                // We use the `windowInsetsAnimationsEnabled` parameter to enable animated
                // insets support. This allows our `ConversationContent` to animate with the
                // on-screen keyboard (IME) as it enters/exits the screen.
                .start(windowInsetsAnimationsEnabled = true)

            setContent{

                CompositionLocalProvider(
                    LocalBackPressedDispatcher provides requireActivity().onBackPressedDispatcher,
                    LocalWindowInsets provides windowInsets,
                ) {
                    JetChatAppTheme {
                    }
                }

            }
        }
        return composeView
    }
}
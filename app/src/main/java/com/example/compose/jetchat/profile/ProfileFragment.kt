package com.example.compose.jetchat.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.compose.jetchat.MainViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ViewWindowInsetObserver

class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val userId = arguments?.getString("userId")
        viewModel.setUserId(userId)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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

            setContent {
                val userData by viewModel.userData.observeAsState()
                CompositionLocalProvider(LocalWindowInsets provides windowInsets) {
                    if (userData == null) {
                        ProfileError()
                    } else {
                        ProfileScreen(userData = userData!!, onNavIconPressed = {
                            activityViewModel.openDrawer()
                        })
                    }
                }
            }
        }
        return composeView
    }
}
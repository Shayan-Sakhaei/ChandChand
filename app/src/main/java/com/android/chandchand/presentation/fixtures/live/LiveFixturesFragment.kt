package com.android.chandchand.presentation.fixtures.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.chandchand.presentation.ui.components.LiveFixturesScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LiveFixturesFragment : Fragment() {

    private val viewModel: LiveFixturesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sendIntent(LiveFixturesIntent.GetLiveFixtures)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ChandChandTheme {
                    LiveFixturesScreen(viewModel)
                }
            }
        }
    }

    private fun sendIntent(intent: LiveFixturesIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(intent)
        }
    }
}
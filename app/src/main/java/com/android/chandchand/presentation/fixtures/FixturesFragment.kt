package com.android.chandchand.presentation.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.chandchand.presentation.ui.components.FixturesScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FixturesFragment : Fragment() {

    private val viewModel: FixturesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sendIntent(FixturesIntent.GetFixtures)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ChandChandTheme {
                    FixturesScreen(viewModel)
                }
            }
        }
    }

    private fun sendIntent(intent: FixturesIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(intent)
        }
    }
}

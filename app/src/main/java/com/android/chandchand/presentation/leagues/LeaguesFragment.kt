package com.android.chandchand.presentation.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.chandchand.presentation.ui.components.LeaguesScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LeaguesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ChandChandTheme {
                    LeaguesScreen { selectedLeagueTitleModel ->
                        findNavController().navigate(
                            LeaguesFragmentDirections.actionLeaguesFragmentToStandingsFragment(
                                selectedLeagueTitleModel.id, selectedLeagueTitleModel.titleResId
                            )
                        )
                    }
                }
            }
        }
    }
}
package com.android.chandchand.presentation.predictions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.chandchand.presentation.ui.components.PredictionsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PredictionsFragment : Fragment() {

    private val viewModel: PredictionsViewModel by viewModels()

    private val args: PredictionsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.prepareState(
            args.homeTeamLogo ?: "",
            args.awayTeamLogo ?: "",
            args.date ?: "",
            args.time ?: ""
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ChandChandTheme {
                    PredictionsScreen(viewModel = viewModel,
                        { drawable -> viewModel.palette(drawable, HomeAway.HOME) },
                        { drawable -> viewModel.palette(drawable, HomeAway.AWAY) })
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.send(PredictionsIntent.GetPredictions(args.fixtureId))
    }
}
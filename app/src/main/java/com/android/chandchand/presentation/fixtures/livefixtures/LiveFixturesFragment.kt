package com.android.chandchand.presentation.fixtures.livefixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.chandchand.databinding.FragmentLiveFixturesBinding
import com.android.chandchand.presentation.common.IView
import com.android.chandchand.presentation.common.LeagueFixturesClickListener
import com.android.chandchand.presentation.model.LeagueModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LiveFixturesFragment : Fragment(), LeagueFixturesClickListener,
    IView<LiveFixturesState> {

    private val viewModel: LiveFixturesViewModel by viewModels {
        defaultViewModelProviderFactory
    }

    private var _binding: FragmentLiveFixturesBinding? = null
    private val binding get() = _binding!!

    private lateinit var liveFixturesController: LiveFixturesController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveFixturesController = LiveFixturesController(this)
        sendIntent(LiveFixturesIntent.GetLiveFixtures)
        viewModel.state.onEach { state ->
            render(state)
        }.launchIn(lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLiveFixturesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ervLiveFixtures.setController(liveFixturesController)
        setUp()
    }

    private fun sendIntent(intent: LiveFixturesIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(intent)
        }
    }


    override fun render(state: LiveFixturesState) {
        with(state) {
            liveFixturesController.setData(liveFixtures)
        }
    }

    private fun setUp() {
        binding.ibClose.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onHeaderClicked(leagueModel: LeagueModel) {
        viewModel.onLeagueTapped(leagueModel)
    }

    override fun onPredictionClicked(fixtureId: Int, homeTeamLogo: String?, awayTeamLogo: String?) {
        findNavController().navigate(
            LiveFixturesFragmentDirections.actionLiveFixturesFragmentToPredictionsFragment(
                fixtureId, homeTeamLogo, awayTeamLogo
            )
        )
    }
}
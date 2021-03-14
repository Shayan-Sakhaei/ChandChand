package com.android.chandchand.presentation.fixtures.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.chandchand.databinding.FragmentDayAfterTomorrowFixturesBinding
import com.android.chandchand.presentation.common.IView
import com.android.chandchand.presentation.common.LeagueFixturesClickListener
import com.android.chandchand.presentation.fixtures.*
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.utils.getDateFromToday
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DayAfterTomorrowFixturesFragment : Fragment(), LeagueFixturesClickListener,
    IView<FixturesState> {

    private val viewModel: FixturesViewModel by viewModels {
        defaultViewModelProviderFactory
    }

    private var _binding: FragmentDayAfterTomorrowFixturesBinding? = null
    private val binding get() = _binding!!

    private lateinit var fixturesController: FixturesController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fixturesController = FixturesController(this)
        sendIntent(FixturesIntent.GetFixtures(getDateFromToday(2)))
        viewModel.state.onEach { state ->
            render(state)
        }.launchIn(lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDayAfterTomorrowFixturesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ervDayAfterTomorrowFixtures.setController(fixturesController)
    }

    private fun sendIntent(intent: FixturesIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(intent)
        }
    }

    override fun render(state: FixturesState) {
        with(state) {
            fixturesController.setData(fixtures)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onHeaderClicked(leagueModel: LeagueModel) {
        viewModel.onLeagueHeaderTapped(leagueModel)
    }

    override fun onPredictionClicked(fixtureId: Int, homeTeamLogo: String?, awayTeamLogo: String?) {
        findNavController().navigate(
            FixturesFragmentDirections.actionFixturesFragmentToPredictionsFragment(
                fixtureId, homeTeamLogo, awayTeamLogo
            )
        )
    }
}
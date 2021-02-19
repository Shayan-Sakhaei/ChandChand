package com.android.chandchand.presentation.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.android.chandchand.R
import com.android.chandchand.databinding.FragmentTomorrowFixturesBinding
import com.android.chandchand.presentation.common.IView
import com.android.chandchand.presentation.common.LeagueFixturesClickListener
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.utils.WeekDay
import com.android.chandchand.presentation.utils.getDateFromToday
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TomorrowFixturesFragment : Fragment(), LeagueFixturesClickListener,
    IView<FixturesState> {

    private val viewModel: FixturesViewModel by navGraphViewModels(R.id.fixtures_graph) {
        defaultViewModelProviderFactory
    }

    private var _binding: FragmentTomorrowFixturesBinding? = null
    private val binding get() = _binding!!

    private lateinit var fixturesController: FixturesController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fixturesController = FixturesController(this)
        sendIntent(FixturesIntent.GetFixtures(getDateFromToday(1), WeekDay.Tomorrow))
        viewModel.state.onEach { state ->
            render(state)
        }.launchIn(lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTomorrowFixturesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ervTomorrowFixtures.setController(fixturesController)
    }

    private fun sendIntent(intent: FixturesIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(intent)
        }
    }

    override fun render(state: FixturesState) {
        with(state) {
            fixturesController.setData(tomorrowFixtures)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onHeaderClicked(leagueModel: LeagueModel) {
        viewModel.tomorrowLeagueTapped(leagueModel)
    }

    override fun onPredictionClicked(fixtureId: Int, homeTeamLogo: String?, awayTeamLogo: String?) {
        findNavController().navigate(
            FixturesFragmentDirections.actionFixturesFragmentToPredictionsFragment(
                fixtureId, homeTeamLogo, awayTeamLogo
            )
        )
    }
}
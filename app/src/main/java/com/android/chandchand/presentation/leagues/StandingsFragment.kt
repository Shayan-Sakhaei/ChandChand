package com.android.chandchand.presentation.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.android.chandchand.R
import com.android.chandchand.databinding.FragmentStandingsBinding
import com.android.chandchand.domain.entities.StandingEntity
import com.android.chandchand.presentation.common.IView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class StandingsFragment : Fragment(), IView<LeaguesState> {

    private val viewModel: LeaguesViewModel by navGraphViewModels(R.id.leagues_graph) {
        defaultViewModelProviderFactory
    }

    private var _binding: FragmentStandingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStandingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedLeagueTitleModel.observe(viewLifecycleOwner) { selectedLeagueTitleModel ->
            binding.tvTitle.text = selectedLeagueTitleModel.name
            sendIntent(LeaguesIntent.GetStandings(selectedLeagueTitleModel.id))
        }
        viewModel.state.onEach { state ->
            render(state)
        }.launchIn(lifecycleScope)
        setUp()
    }

    private fun sendIntent(intent: LeaguesIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(intent)
        }
    }

    override fun render(state: LeaguesState) {
        with(state) {
            populateStandings(standings)
        }
    }

    private fun populateStandings(standings: List<StandingEntity>) {
        binding.ervStandings.withModels {
            standings.map {
                StandingEpoxyItem(it)
                    .setSelectedTeamIdCallback { selectedTeamId ->
                    }
                    .id(it.team_id).addTo(this)
            }
        }
    }

    private fun setUp() {
        binding.ibBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
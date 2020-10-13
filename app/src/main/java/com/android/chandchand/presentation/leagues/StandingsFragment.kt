package com.android.chandchand.presentation.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.android.chandchand.R
import com.android.chandchand.databinding.FragmentStandingsBinding
import com.android.chandchand.domain.entities.StandingEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StandingsFragment : Fragment() {

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
            viewModel.getStandings(selectedLeagueTitleModel.id)
        }
        viewModel.standings.observe(viewLifecycleOwner, ::populateStandings)
        setUp()
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
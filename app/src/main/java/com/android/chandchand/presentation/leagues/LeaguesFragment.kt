package com.android.chandchand.presentation.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.android.chandchand.R
import com.android.chandchand.databinding.FragmentLeaguesBinding
import com.android.chandchand.presentation.model.LeagueTitleModel
import com.android.chandchand.presentation.model.LeaguesTitleList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LeaguesFragment : Fragment() {

    private val viewModel: LeaguesViewModel by navGraphViewModels(R.id.leagues_graph) {
        defaultViewModelProviderFactory
    }

    @Inject
    lateinit var leaguesTitleList: LeaguesTitleList

    private var _binding: FragmentLeaguesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeaguesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateLeaguesTitle(leaguesTitleList.leagues)
    }

    private fun populateLeaguesTitle(leaguesTitle: List<LeagueTitleModel>) {
        binding.ervLeagues.withModels {
            leaguesTitle.map {
                LeagueTitleEpoxyItem(it)
                    .setSelectedLeagueCallback { selectedLeagueTitleModel ->
                        viewModel._selectedLeagueTitleModel.value = selectedLeagueTitleModel
//                        viewModel.getStandings(selectedLeagueTitleModel.id)
                        findNavController().navigate(LeaguesFragmentDirections.actionLeaguesFragmentToStandingsFragment())
                    }
                    .id(it.id).addTo(this)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
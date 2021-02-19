package com.android.chandchand.presentation.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewGroupCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.android.chandchand.R
import com.android.chandchand.databinding.FragmentLeaguesBinding
import com.android.chandchand.presentation.model.LeagueTitleModel
import com.android.chandchand.presentation.model.LeaguesTitleList
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LeaguesFragment : Fragment() {

    private val viewModel: LeaguesViewModel by navGraphViewModels(R.id.leagues_graph) {
        defaultViewModelProviderFactory
    }

    @Inject
    lateinit var leaguesTitleList: LeaguesTitleList

    private var _binding: FragmentLeaguesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeaguesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewGroupCompat.setTransitionGroup(binding.ervLeagues, true)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        populateLeaguesTitle(leaguesTitleList.leagues)
    }

    private fun populateLeaguesTitle(leaguesTitle: List<LeagueTitleModel>) {
        binding.ervLeagues.withModels {
            leaguesTitle.map {
                LeagueTitleEpoxyItem(it)
                    .setSelectedLeagueCallback { materialCardView, selectedLeagueTitleModel ->
                        viewModel._selectedLeagueTitleModel.value = selectedLeagueTitleModel

                        exitTransition = MaterialElevationScale(false).apply {
                            duration =
                                resources.getInteger(R.integer.reply_motion_duration_long).toLong()
                        }
                        reenterTransition = MaterialElevationScale(true).apply {
                            duration = 1000
                            resources.getInteger(R.integer.reply_motion_duration_long).toLong()
                        }
                        val standingsTransitionName = getString(R.string.standings_transition_name)
                        val extras = FragmentNavigatorExtras(
                            materialCardView to standingsTransitionName
                        )
                        findNavController().navigate(
                            LeaguesFragmentDirections.actionLeaguesFragmentToStandingsFragment(),
                            extras
                        )
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
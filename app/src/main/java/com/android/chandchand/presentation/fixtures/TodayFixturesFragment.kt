package com.android.chandchand.presentation.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.android.chandchand.R
import com.android.chandchand.databinding.FragmentTodayFixturesBinding
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.utils.WeekDay
import com.android.chandchand.presentation.utils.getDateFromToday
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFixturesFragment : Fragment(), FixturesController.HeaderClickListener {

    private val viewModel: FixturesViewModel by navGraphViewModels(R.id.fixtures_graph) {
        defaultViewModelProviderFactory
    }

    private var _binding: FragmentTodayFixturesBinding? = null
    private val binding get() = _binding!!

    private lateinit var fixturesController: FixturesController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFixtures(getDateFromToday(0), WeekDay.Today)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fixturesController = FixturesController(this)
        _binding = FragmentTodayFixturesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ervTodayFixtures.setController(fixturesController)
        viewModel.todayFixtures.observe(viewLifecycleOwner) {
            fixturesController.setData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onHeaderClicked(leagueModel: LeagueModel) {
        viewModel.todayLeagueTapped(leagueModel)
    }
}
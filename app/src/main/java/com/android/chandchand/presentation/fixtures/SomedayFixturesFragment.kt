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
import com.android.chandchand.databinding.FragmentSomedayFixturesBinding
import com.android.chandchand.presentation.common.IView
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.utils.WeekDay
import com.android.chandchand.presentation.utils.toDate
import dagger.hilt.android.AndroidEntryPoint
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SomedayFixturesFragment : Fragment(), FixturesController.HeaderClickListener,
    IView<FixturesState> {

    private val viewModel: FixturesViewModel by navGraphViewModels(R.id.fixtures_graph) {
        defaultViewModelProviderFactory
    }

    private var _binding: FragmentSomedayFixturesBinding? = null
    private val binding get() = _binding!!

    lateinit var datePicker: PersianDatePickerDialog

    private lateinit var fixturesController: FixturesController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fixturesController = FixturesController(this)
        viewModel.state.onEach { state ->
            render(state)
        }.launchIn(lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSomedayFixturesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ervSomedayFixtures.setController(fixturesController)
        viewModel.somedayDateModel.observe(viewLifecycleOwner) { dateModel ->
            binding.tvDateDescription.text = dateModel.description
            sendIntent(FixturesIntent.GetFixtures(dateModel.date, WeekDay.Someday))
        }
        setUp()
    }

    private fun sendIntent(intent: FixturesIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(intent)
        }
    }

    override fun render(state: FixturesState) {
        with(state) {
            fixturesController.setData(somedayFixtures)
        }
    }

    private fun setUp() {
        binding.ibClose.setOnClickListener {
            findNavController().navigateUp()
        }

        datePicker = PersianDatePickerDialog(requireContext())
            .setPositiveButtonResource(R.string.choose)
            .setNegativeButtonResource(R.string.quit)
            .setTodayButtonResource(R.string.today)
            .setMinYear(1388)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setListener(object : Listener {
                override fun onDateSelected(persianCalendar: PersianCalendar?) {
                    persianCalendar?.run {
                        binding.tvDateDescription.text = String.format(
                            "%s  %s",
                            getString(R.string.fixtures_of),
                            this.persianLongDate
                        )
                        val selectedDate = this.timeInMillis.toDate()
                        viewModel.getFixtures(selectedDate, WeekDay.Someday)
                    }
                }

                override fun onDismissed() {}
            })

        binding.ibCalendar.setOnClickListener {
            datePicker.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onHeaderClicked(leagueModel: LeagueModel) {
        viewModel.somedayLeagueTapped(leagueModel)
    }
}
package com.android.chandchand.presentation.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.android.chandchand.R
import com.android.chandchand.databinding.FragmentFixturesBinding
import com.android.chandchand.presentation.utils.toDate
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FixturesFragment : Fragment() {

    private var _binding: FragmentFixturesBinding? = null
    private val binding get() = _binding!!

    lateinit var datePicker: PersianDatePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFixturesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        binding.fixturesViewPager.adapter = FixturesPagerAdapter(this)
        TabLayoutMediator(binding.fixturesTabLayout, binding.fixturesViewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.yesterday)
                }
                1 -> {
                    tab.text = getString(R.string.today)
                }
                2 -> {
                    tab.text = getString(R.string.tomorrow)
                }
                3 -> {
                    tab.text = getString(R.string.day_after_tomorrow)
                }
            }
        }.attach()

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
                        val selectedDate = this.timeInMillis.toDate()
                        val selectedDateDescription = String.format(
                            "%s  %s",
                            getString(R.string.fixtures_of),
                            this.persianLongDate
                        )
                        findNavController().navigate(
                            FixturesFragmentDirections.actionFixturesFragmentToSomedayFixturesFragment(
                                selectedDate,
                                selectedDateDescription
                            )
                        )
                    }
                }

                override fun onDismissed() {}
            })

        binding.ibCalendar.setOnClickListener {
            datePicker.show()
        }

        binding.ibTv.setOnClickListener {
            findNavController().navigate(FixturesFragmentDirections.actionFixturesFragmentToLiveFixturesFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

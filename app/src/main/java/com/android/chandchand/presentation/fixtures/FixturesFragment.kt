package com.android.chandchand.presentation.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.chandchand.R
import com.android.chandchand.presentation.utils.toDate
import dagger.hilt.android.AndroidEntryPoint
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FixturesFragment : Fragment() {

    private val viewModel: FixturesViewModel by viewModels()

    lateinit var datePicker: PersianDatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sendIntent(FixturesIntent.GetFixtures)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
/*                ChandChandTheme {
                    FixturesScreen(
                        viewModel,
                        onNavigate = { navDirections ->
                            findNavController().navigate(navDirections)
                        },
                        onCalendarClick = { datePicker.show() }
                    )
                }*/
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }

    private fun sendIntent(intent: FixturesIntent) {
        lifecycleScope.launch {
            viewModel.intents.send(intent)
        }
    }
}

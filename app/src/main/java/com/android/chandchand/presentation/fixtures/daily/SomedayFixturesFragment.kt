package com.android.chandchand.presentation.fixtures.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.chandchand.R
import com.android.chandchand.presentation.fixtures.FixturesIntent
import com.android.chandchand.presentation.fixtures.FixturesViewModel
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.chandchand.presentation.ui.components.SomedayFixturesScreen
import com.android.chandchand.presentation.utils.toDate
import dagger.hilt.android.AndroidEntryPoint
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SomedayFixturesFragment : Fragment() {

    private val viewModel: FixturesViewModel by viewModels()

    private val args: SomedayFixturesFragmentArgs by navArgs()

    lateinit var datePicker: PersianDatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setSomedayDate(args.selectedDate, args.selectedDateDescription)
        sendIntent(FixturesIntent.GetSomedayFixtures(viewModel.state.value.somedayDate))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ChandChandTheme {
                    SomedayFixturesScreen(
                        viewModel = viewModel,
                        onNavigate = { navDirections ->
                            findNavController().navigate(navDirections)
                        },
                        onCalendarClick = { datePicker.show() })
                }
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
                        viewModel.setSomedayDate(
                            selectedDate,
                            String.format(
                                "%s  %s",
                                getString(R.string.fixtures_of),
                                this.persianLongDate
                            )
                        )
                        viewModel.getSomedayFixtures(viewModel.state.value.somedayDate)
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
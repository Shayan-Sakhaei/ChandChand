package com.android.chandchand.presentation.fixtures

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.chandchand.presentation.fixtures.daily.DayAfterTomorrowFixturesFragment
import com.android.chandchand.presentation.fixtures.daily.TodayFixturesFragment
import com.android.chandchand.presentation.fixtures.daily.TomorrowFixturesFragment
import com.android.chandchand.presentation.fixtures.daily.YesterdayFixturesFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class FixturesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = FixtureTabsModel.values().size

    override fun createFragment(position: Int): Fragment {
        return when (FixtureTabsModel.values()[position]) {
            FixtureTabsModel.Yesterday -> YesterdayFixturesFragment()
            FixtureTabsModel.Today -> TodayFixturesFragment()
            FixtureTabsModel.Tomorrow -> TomorrowFixturesFragment()
            FixtureTabsModel.DayAfterTomorrow -> DayAfterTomorrowFixturesFragment()
        }
    }
}
package com.android.chandchand.presentation.fixtures

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class FixturesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                YesterdayFixturesFragment()
            }
            1 -> {
                TodayFixturesFragment()
            }
            2 -> {
                TomorrowFixturesFragment()
            }
            3 -> {
                DayAfterTomorrowFixturesFragment()
            }
            else -> {
                TodayFixturesFragment()
            }
        }
    }
}
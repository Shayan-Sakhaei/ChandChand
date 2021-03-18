package com.android.chandchand.presentation.fixtures

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class FixturesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = FixtureTabsModel.values().size

    override fun createFragment(position: Int): Fragment {
        val tab = FixtureTabsModel.values()[position]
        return FixtureListFragment.newInstance(tab)
    }
}
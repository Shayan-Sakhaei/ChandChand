package com.android.chandchand.presentation.fixtures

import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.chandchand.EspressoIdlingResource
import com.android.chandchand.R
import com.android.chandchand.di.launchFragmentInHiltContainer
import com.android.domain.repositories.FixturesRepository
import com.android.chandchand.fake.FakeFixturesRepository
import com.android.chandchand.presentation.di.fixtures.FixturesModule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@UninstallModules(FixturesModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FixtureListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    val fakeFixturesRepository: com.android.domain.repositories.FixturesRepository = FakeFixturesRepository()

    @Before
    fun setUp() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun fixtures_displayedInUi() {
        launchFragmentInHiltContainer<FixtureListFragment>(
            bundleOf("ARG_FILTER" to FixtureTabsModel.values()[0]),
            R.style.Theme_ChandChand
        )

        onView(withId(R.id.erv_today_fixtures)).check(matches(isDisplayed()))

        onView(withId(R.id.erv_today_fixtures)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
            .check(matches(hasDescendant(withText("لیگ برتر خلیج فارس"))))

        onView(withId(R.id.erv_today_fixtures))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("لیگ برتر خلیج فارس")), click()
                )
            )

        onView(withId(R.id.erv_today_fixtures)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
            .check(matches(hasDescendant(withText("Persepolis FC"))))

        onView(withId(R.id.erv_today_fixtures)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
            .check(matches(hasDescendant(withText("Esteghlal FC"))))
    }
}

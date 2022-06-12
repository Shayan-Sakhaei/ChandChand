package com.android.chandchand.presentation.fixtures.live

import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
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
class LiveFixturesFragmentTest {

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
    fun liveFixtures_displayedInUi() {
        launchFragmentInHiltContainer<LiveFixturesFragment>(bundleOf(), R.style.Theme_ChandChand)

        Espresso.onView(ViewMatchers.withId(R.id.erv_live_fixtures))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.erv_live_fixtures)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("لیگ برتر خلیج فارس"))))

        Espresso.onView(ViewMatchers.withId(R.id.erv_live_fixtures))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("لیگ برتر خلیج فارس")),
                    ViewActions.click()
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.erv_live_fixtures)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Persepolis FC"))))

        Espresso.onView(ViewMatchers.withId(R.id.erv_live_fixtures)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Esteghlal FC"))))
    }
}
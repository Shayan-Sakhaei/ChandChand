package com.android.chandchand.presentation.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.android.chandchand.fake.FakeFixturesRepository
import com.android.chandchand.presentation.fixtures.FixturesViewModel
import com.android.chandchand.presentation.mapper.FixtureEntityUiMapper
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.domain.usecase.GetFixturesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FixturesScreenTest {

    private lateinit var fakeFixturesRepository: FakeFixturesRepository
    private lateinit var getFixturesUseCase: GetFixturesUseCase
    private lateinit var entityUiMapper: FixtureEntityUiMapper
    private lateinit var viewModel: FixturesViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        fakeFixturesRepository = FakeFixturesRepository()
        getFixturesUseCase = GetFixturesUseCase(fakeFixturesRepository)
        entityUiMapper = FixtureEntityUiMapper()
        viewModel = FixturesViewModel(getFixturesUseCase, entityUiMapper)
    }

    @Test
    fun topAppBar_displayedInUi() {
        composeTestRule.setContent {
            ChandChandTheme {
                FixturesScreen(viewModel = viewModel, onNavigate = {}) {}
            }
        }

        composeTestRule.onNodeWithText("بازی ها").assertExists()
        composeTestRule.onNodeWithText("بازی ها").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("calendar").assertExists()
        composeTestRule.onNodeWithContentDescription("calendar").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("live").assertExists()
        composeTestRule.onNodeWithContentDescription("live").assertIsDisplayed()
    }

    @Test
    fun tabLayout_displayedInUi() {
        composeTestRule.setContent {
            ChandChandTheme {
                FixturesScreen(viewModel = viewModel, onNavigate = {}) {}
            }
        }

        composeTestRule.onNodeWithContentDescription("دیروز").assertExists()
        composeTestRule.onNodeWithContentDescription("دیروز").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("امروز").assertExists()
        composeTestRule.onNodeWithContentDescription("امروز").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("فردا").assertExists()
        composeTestRule.onNodeWithContentDescription("فردا").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("پس فردا").assertExists()
        composeTestRule.onNodeWithContentDescription("پس فردا").assertIsDisplayed()
    }

    @Test
    fun pager_displayedInUi() {
        composeTestRule.setContent {
            ChandChandTheme {
                FixturesScreen(viewModel = viewModel, onNavigate = {}) {}
            }
        }

        composeTestRule.onNodeWithContentDescription("HorizontalPager").assertExists()
        composeTestRule.onNodeWithContentDescription("HorizontalPager").assertIsDisplayed()
    }
}
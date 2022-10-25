package com.android.chandchand.presentation.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.android.chandchand.fake.FakeData
import com.android.chandchand.presentation.ui.theme.ChandChandTheme
import org.junit.Rule
import org.junit.Test

class FixtureTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun fixture_displayedInUi() {
        composeTestRule.setContent {
            ChandChandTheme {
                Fixture(
                    fixture = FakeData.persianGulfCupFixture,
                    onPredictionClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Persepolis FC").assertExists()
        composeTestRule.onNodeWithText("Persepolis FC").assertIsDisplayed()

        composeTestRule.onNodeWithText("Esteghlal FC").assertExists()
        composeTestRule.onNodeWithText("Esteghlal FC").assertIsDisplayed()

        composeTestRule.onNodeWithText("4").assertExists()
        composeTestRule.onNodeWithText("4").assertIsDisplayed()

        composeTestRule.onNodeWithText("2").assertExists()
        composeTestRule.onNodeWithText("2").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("home team logo").assertExists()
        composeTestRule.onNodeWithContentDescription("home team logo").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("prediction icon").assertExists()
        composeTestRule.onNodeWithContentDescription("prediction icon").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("away team logo").assertExists()
        composeTestRule.onNodeWithContentDescription("away team logo").assertIsDisplayed()
    }
}

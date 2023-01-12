package com.anonymous.fixtures.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.anonymous.designsystem.theme.ChandChandTheme
import com.anonymous.fixtures.Fake
import org.junit.Rule
import org.junit.Test

class FixturesPerLeagueTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun fixtures_displayedInUi() {
        composeTestRule.setContent {
            ChandChandTheme {
                FixturesPerDay(
                    fixtures = Fake.fixturesPerLeague,
                    onHeaderClick = {},
                    onPredictionClick = {})
            }
        }

        composeTestRule.onNodeWithContentDescription("لیگ برتر خلیج فارس logo").assertExists()
        composeTestRule.onNodeWithContentDescription("لیگ برتر خلیج فارس logo").assertIsDisplayed()

        composeTestRule.onNodeWithText("لیگ برتر خلیج فارس").assertExists()
        composeTestRule.onNodeWithText("لیگ برتر خلیج فارس").assertIsDisplayed()

        composeTestRule.onNodeWithText("Persepolis FC").assertExists()
        composeTestRule.onNodeWithText("Persepolis FC").assertIsDisplayed()

        composeTestRule.onNodeWithText("Esteghlal FC").assertExists()
        composeTestRule.onNodeWithText("Esteghlal FC").assertIsDisplayed()


        composeTestRule.onNodeWithContentDescription("لیگ برتر انگلیس logo").assertExists()
        composeTestRule.onNodeWithContentDescription("لیگ برتر انگلیس logo").assertIsDisplayed()

        composeTestRule.onNodeWithText("لیگ برتر انگلیس").assertExists()
        composeTestRule.onNodeWithText("لیگ برتر انگلیس").assertIsDisplayed()

        composeTestRule.onNodeWithText("Manchester City").assertExists()
        composeTestRule.onNodeWithText("Manchester City").assertIsDisplayed()

        composeTestRule.onNodeWithText("Aston Villa").assertExists()
        composeTestRule.onNodeWithText("Aston Villa").assertIsDisplayed()

        composeTestRule.onAllNodesWithContentDescription("home team logo").assertCountEquals(2)
        composeTestRule.onAllNodesWithContentDescription("prediction icon").assertCountEquals(2)
        composeTestRule.onAllNodesWithContentDescription("away team logo").assertCountEquals(2)
    }
}
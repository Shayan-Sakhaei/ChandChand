package com.android.chandchand.presentation.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.android.chandchand.presentation.leagues.compose.LeaguesScreen
import com.android.chandchand.presentation.ui.theme.ChandChandTheme
import org.junit.Rule
import org.junit.Test

class LeaguesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun leagueTitles_displayedInUi() {
        composeTestRule.setContent {
            ChandChandTheme {
                LeaguesScreen(onNavigate = { destination, route -> })
            }
        }

        composeTestRule.onNodeWithText("لیگ برتر خلیج فارس").assertExists()
        composeTestRule.onNodeWithText("لیگ برتر خلیج فارس").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("لیگ برتر خلیج فارس logo").assertExists()
        composeTestRule.onNodeWithContentDescription("لیگ برتر خلیج فارس logo").assertIsDisplayed()

        composeTestRule.onNodeWithText("لیگ برتر انگلیس").assertExists()
        composeTestRule.onNodeWithText("لیگ برتر انگلیس").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("لیگ برتر انگلیس logo").assertExists()
        composeTestRule.onNodeWithContentDescription("لیگ برتر انگلیس logo").assertIsDisplayed()

        composeTestRule.onNodeWithText("لالیگا اسپانیا").assertExists()
        composeTestRule.onNodeWithText("لالیگا اسپانیا").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("لالیگا اسپانیا logo").assertExists()
        composeTestRule.onNodeWithContentDescription("لالیگا اسپانیا logo").assertIsDisplayed()

        composeTestRule.onNodeWithText("بوندسلیگا آلمان").assertExists()
        composeTestRule.onNodeWithText("بوندسلیگا آلمان").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("بوندسلیگا آلمان logo").assertExists()
        composeTestRule.onNodeWithContentDescription("بوندسلیگا آلمان logo").assertIsDisplayed()

        composeTestRule.onNodeWithText("سری آ ایتالیا").assertExists()
        composeTestRule.onNodeWithText("سری آ ایتالیا").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("سری آ ایتالیا logo").assertExists()
        composeTestRule.onNodeWithContentDescription("سری آ ایتالیا logo").assertIsDisplayed()

        composeTestRule.onNodeWithText("لیگ یک فرانسه").assertExists()
        composeTestRule.onNodeWithText("لیگ یک فرانسه").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("لیگ یک فرانسه logo").assertExists()
        composeTestRule.onNodeWithContentDescription("لیگ یک فرانسه logo").assertIsDisplayed()
    }
}
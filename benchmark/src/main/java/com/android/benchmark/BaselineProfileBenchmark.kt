package com.android.benchmark

import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaselineProfileBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startupAndPrimaryUserFlowNoCompilation() {
        startupAndPrimaryUserFlow(CompilationMode.None())
    }

    @Test
    fun startupAndPrimaryUserFlowBaselineProfile() {
        startupAndPrimaryUserFlow(
            CompilationMode.Partial(
                baselineProfileMode = BaselineProfileMode.Require
            )
        )
    }

    private fun startupAndPrimaryUserFlow(compilationMode: CompilationMode) {
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(StartupTimingMetric()),
            iterations = 5,
            startupMode = StartupMode.COLD,
            compilationMode = compilationMode
        ) {

            startActivityAndWait()

            val todayTab = device.findObject(By.desc(TODAY_TAB_DESCRIPTION))
            todayTab.click()
            device.waitForIdle()

            val tomorrowTab = device.findObject(By.desc(TOMORROW_TAB_DESCRIPTION))
            tomorrowTab.click()
            device.waitForIdle()

            val dayAfterTomorrowTab = device.findObject(By.desc(DAYAFTERTOMORROW_TAB_DESCRIPTION))
            dayAfterTomorrowTab.click()
            device.waitForIdle()

            val yesterdayTab = device.findObject(By.desc(YESTERDAY_TAB_DESCRIPTION))
            yesterdayTab.click()
            device.waitForIdle()

            val leaguesBottomNavigation = device.findObject(By.text(LEAGUES_NAVIGATION_TEXT))
            leaguesBottomNavigation.click()
            device.waitForIdle()
        }
    }

    companion object {
        private const val PACKAGE_NAME = "com.android.chandchand"
        private const val YESTERDAY_TAB_DESCRIPTION = "دیروز"
        private const val TODAY_TAB_DESCRIPTION = "امروز"
        private const val TOMORROW_TAB_DESCRIPTION = "فردا"
        private const val DAYAFTERTOMORROW_TAB_DESCRIPTION = "پس فردا"
        private const val LEAGUES_NAVIGATION_TEXT = "لیگ ها"
    }
}
package com.android.chandchand.presentation.fixtures

import android.os.Parcel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FixtureTabsModelTest {

    @Test
    fun `test fixtureTabsModel parcelize`() {
        FixtureTabsModel.values().forEach { model ->
            val parcel = Parcel.obtain().apply {
                writeParcelable(model, 0)
                setDataPosition(0)
            }

            val createdFromParcel = parcel.readParcelable<FixtureTabsModel>(FixtureTabsModel::class.java.classLoader)
            Assert.assertEquals(model, createdFromParcel)
        }
    }
}
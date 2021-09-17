package com.hiberus.mobile.android.session

import com.hiberus.mobile.android.session.preferences.Preferences
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class AppSessionDataImplSourceImplTest {

    private val preferences = mock<Preferences>()

    @After
    fun tearDown() {
        verifyNoMoreInteractions(preferences)
    }

    @Test
    fun `should return default value if preference does not exists`() {
        val key = "KEY_PREFERENCE"
        val defaultValue = "foo"
        whenever(preferences.getPreference(key, defaultValue)) doReturn defaultValue

        val pref = preferences.getPreference(key, defaultValue)

        verify(preferences).getPreference(key, defaultValue)
        assertEquals(defaultValue, pref)
    }
}
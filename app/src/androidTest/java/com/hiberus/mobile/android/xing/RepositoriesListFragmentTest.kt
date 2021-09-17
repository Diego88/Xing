package com.hiberus.mobile.android.xing

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.hiberus.mobile.android.xing.repositories.RepositoriesListAdapter
import com.hiberus.mobile.android.xing.repositories.RepositoriesListFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
@ExperimentalCoroutinesApi
class RepositoriesListFragmentTest {

    private lateinit var scenario: FragmentScenario<RepositoriesListFragment>
    private lateinit var navController: NavController

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(Bundle(), R.style.Theme_Xing)
        navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            it.view?.let { v -> Navigation.setViewNavController(v, navController) }
        }
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.hiberus.mobile.android.xing.debug", appContext.packageName)
    }

    @Test
    fun showRepositoriesListWhenLaunchFragment() {
        onView(withId(R.id.rv_repositories_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun repositoriesListIsScrollable() {
        onView(withId(R.id.rv_repositories_list))
            .perform(RecyclerViewActions.scrollToPosition<RepositoriesListAdapter.ViewHolder>(18))
    }
}
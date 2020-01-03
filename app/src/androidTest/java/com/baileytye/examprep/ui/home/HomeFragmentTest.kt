package com.baileytye.examprep.ui.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.baileytye.examprep.R
import com.baileytye.examprep.data.User
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
@MediumTest
class HomeFragmentTest {

    @Test
    fun testNavigation() {
        //GIVEN
        val scenario = launchFragmentInContainer<HomeFragment>(null, R.style.AppTheme)

        val navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        //WHEN - click on 'two-way databinding'
        onView(withId(R.id.buttonMirrorFragment)).perform(click())

        //THEN
        verify(navController).navigate(
            HomeFragmentDirections.actionHomeFragmentToMirrorTextFragment()
        )
    }

    @Test
    fun testSendUserNavigation() {
        //GIVEN
        val scenario = launchFragmentInContainer<HomeFragment>(null, R.style.AppTheme)

        val navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        val firstName = "first"
        val lastName = "last"

        //WHEN - user filled -> click button to go to user fragment
        onView(withId(R.id.editTextFirstName)).perform(replaceText(firstName))
        onView(withId(R.id.editTextLastName)).perform(replaceText(lastName))

        onView(withId(R.id.buttonSendUser)).perform(click())

        //THEN
        verify(navController).navigate(
            HomeFragmentDirections.actionHomeFragmentToReceiveTextFragment(
                User(
                    firstName,
                    lastName
                )
            )
        )
    }
}
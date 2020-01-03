package com.baileytye.examprep.ui.validation

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.baileytye.examprep.R
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class ValidationFragmentTest {

    lateinit var idlingResource: IdlingResource

    @Before
    fun registerIdlingResource() {
        val scenario = launchFragmentInContainer<ValidationFragment>(null, R.style.AppTheme)

        scenario.onFragment {
            idlingResource = it.viewModel.getIdlingResource()
            IdlingRegistry.getInstance().register(idlingResource)
        }
    }

    @After
    fun unregisterIdlingResource() {
        if (::idlingResource.isInitialized) {
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }

    @Test
    fun emailIsMirroredToTextView() {
        //GIVEN/WHEN - text in email field
        val email = "email@email.com"
        onView(withId(R.id.editTextEmail)).perform(ViewActions.replaceText(email))

        //THEN - email matches what was typed
        onView(withId(R.id.textViewEmail)).check(matches(withText(email)))
    }

    @Test
    fun passwordIsMirroredToTextView() {
        //GIVEN/WHEN - text in password field
        val password = "sjdghfi7"
        onView(withId(R.id.editTextPassword)).perform(ViewActions.replaceText(password))

        //THEN - password matches what was typed
        onView(withId(R.id.textViewPassword)).check(matches(withText(password)))
    }

    @Test
    fun validateProducesErrorWhenEmpty() {
        //GIVEN - empty email/password fields

        //WHEN - click validate
        onView(withId(R.id.buttonValidate)).perform(click())

        //Only checks once idling resource is idle - happens after validation is finished
        //THEN - verify error is present on email input layout
        onView(withId(R.id.inputLayoutEmail)).check(matches(hasTextInputLayoutError()))
        onView(withId(R.id.inputLayoutPassword)).check(matches(not(hasTextInputLayoutError())))
    }

    @Test
    fun validateProducesErrorOnPasswordWhenEmailValid() {
        //GIVEN - empty password, valid email fields
        val email = "email@email.com"
        onView(withId(R.id.editTextEmail)).perform(ViewActions.replaceText(email))

        //WHEN - click validate
        onView(withId(R.id.buttonValidate)).perform(click())

        //Only checks once idling resource is idle - happens after validation is finished
        //THEN - verify error is present on password input layout
        onView(withId(R.id.inputLayoutEmail)).check(matches(not(hasTextInputLayoutError())))
        onView(withId(R.id.inputLayoutPassword)).check(matches(hasTextInputLayoutError()))
    }

    @Test
    fun validateNoErrorsWhenBothValid() {
        //GIVEN - valid email/password fields
        val email = "email@email.com"
        val password = "Password1@"
        onView(withId(R.id.editTextEmail)).perform(ViewActions.replaceText(email))
        onView(withId(R.id.editTextPassword)).perform(ViewActions.replaceText(password))

        //WHEN - click validate
        onView(withId(R.id.buttonValidate)).perform(click())

        //Only checks once idling resource is idle - happens after validation is finished
        //THEN - verify error is not present
        onView(withId(R.id.inputLayoutEmail)).check(matches(not(hasTextInputLayoutError())))
        onView(withId(R.id.inputLayoutPassword)).check(matches(not(hasTextInputLayoutError())))
    }

    @Test
    fun validateSnackbarShowsWhenValid() {
        //GIVEN - valid email/password fields
        val email = "email@email.com"
        val password = "Password1@"
        onView(withId(R.id.editTextEmail)).perform(ViewActions.replaceText(email))
        onView(withId(R.id.editTextPassword)).perform(ViewActions.replaceText(password))

        //WHEN - click validate
        onView(withId(R.id.buttonValidate)).perform(click())

        //Only checks once idling resource is idle - happens after validation is finished
        //THEN - verify snackbar shows
        onView(withText(R.string.message_valid)).check(matches(isDisplayed()))
    }
}

fun hasTextInputLayoutError(): Matcher<View> =
    object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) {}

        override fun matchesSafely(item: View?): Boolean {
            if (item !is TextInputLayout) return false
            return item.isErrorEnabled
        }
    }
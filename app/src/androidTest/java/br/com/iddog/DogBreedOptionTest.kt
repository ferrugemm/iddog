package br.com.iddog

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.iddog.presentation.view.MainActivity
import br.com.iddog.presentation.view.user.UserAccountFragmentDirections
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DogBreedOptionTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun setup() {
        activityRule.activity.findNavController(R.id.nav_main_host)
            .navigate(UserAccountFragmentDirections.actionUserAccountFragmentToDogBreedOptionFragment())
    }

    @Test
    fun checkHuskyTextChangeWhenPerfomClick() {
        onView(withId(R.id.ivHusky)).perform(click())
        onView(withId(R.id.mbDogChoseed)).check(matches(withText("Prosseguir com husky")))
    }

    @Test
    fun checkLabradorTextChangeWhenPerfomClick() {
        onView(withId(R.id.ivLabrador)).perform(click())
        onView(withId(R.id.mbDogChoseed)).check(matches(withText("Prosseguir com labrador")))
    }

    @Test
    fun checkHoundTextChangeWhenPerfomClick() {
        onView(withId(R.id.ivHound)).perform(click())
        onView(withId(R.id.mbDogChoseed)).check(matches(withText("Prosseguir com beagle")))
    }

    @Test
    fun checkPugTextChangeWhenPerfomClick() {
        onView(withId(R.id.ivPug)).perform(click())
        onView(withId(R.id.mbDogChoseed)).check(matches(withText("Prosseguir com pug")))
    }
}
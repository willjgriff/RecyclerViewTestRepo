package com.github.willjgriff.playground.lists;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.github.willjgriff.playground.navigation.NavigationActivity;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Will on 06/07/2016.
 */
public class ListsFragmentTest {

    @Rule
    public ActivityTestRule<NavigationActivity> navigationActivityRule = new ActivityTestRule<>(NavigationActivity.class);

    @Test
    public void initialScreen_containsHeaderText() {
        String expectedHeaderText = "Que tal header!";
        Espresso.onView(ViewMatchers.withText(expectedHeaderText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void initialScreen_fabMenuContainsCorrectEntries() {
        Espresso.onView(ViewMatchers.withId(com.getbase.floatingactionbutton.R.id.fab_expand_menu_button)).perform(ViewActions.click());

        String expectedFabItem1 = "Recycler View";
        String expectedFabItem2 = "List View";
        String expectedFabItem3 = "List Fragment";
        String expectedFabItem4 = "Dialog Grid View";
        Espresso.onView(ViewMatchers.withText(expectedFabItem1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(expectedFabItem2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(expectedFabItem3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(expectedFabItem4)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
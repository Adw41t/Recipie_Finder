package com.projects.recipiefinder;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityInstrumentationTest {

    // Preferred JUnit 4 mechanism of specifying the activity to be launched before each test
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Looks for an EditText with id = "R.id.etInput"
    // Types the text "Hello" into the EditText
    // Verifies the EditText has text "Hello"
    @Test
    public void validateEditText() {
//        onView(withId(R.id.etInput)).perform(typeText("Hello")).check(matches(withText("Hello")));
    }

    @Test
    public void testRecyclerView(){
        // Click on the RecyclerView item at position 2
//        onView(withId(R.id.recyler)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        int position = 5;
        onView(withId(R.id.recyler))
                .perform(
                        RecyclerViewActions.scrollToPosition(position),
                        RecyclerViewActions.actionOnItemAtPosition(position, ViewActions.click())
                );
    }
}

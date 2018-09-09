package com.example.bbreda.cardmanager;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bbreda.cardmanager.presentation.home.HomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity>
            mActivityRule = new ActivityTestRule<>(HomeActivity.class, false, true);

    @Test
    public void whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.imageView_card)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_due_date)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_show_due_date)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_invoice_value)).check(matches(isDisplayed()));
    }
}

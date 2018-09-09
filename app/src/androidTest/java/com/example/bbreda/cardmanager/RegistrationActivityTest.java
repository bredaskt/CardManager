package com.example.bbreda.cardmanager;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bbreda.cardmanager.presentation.registration.RegistrationActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RegistrationActivityTest {

    @Rule
    public ActivityTestRule<RegistrationActivity>
            mActivityRule = new ActivityTestRule<>(RegistrationActivity.class, false, true);

    @Test
    public void whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.et_name)).check(matches(isDisplayed()));
        onView(withId(R.id.et_email)).check(matches(isDisplayed()));
        onView(withId(R.id.et_phone_number)).check(matches(isDisplayed()));
        onView(withId(R.id.et_card_number)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_read_card_from_cam)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_account_request)).check(matches(isDisplayed()));
    }
}

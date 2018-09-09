package com.example.bbreda.cardmanager;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bbreda.cardmanager.presentation.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity>
            mActivityRule = new ActivityTestRule<>(LoginActivity.class, false, true);

    @Test
    public void whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.et_email_log)).check(matches(isDisplayed()));
        onView(withId(R.id.et_senha)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_registration)).check(matches(isDisplayed()));
    }

    @Test
    public void whenEmailIsEmpty_andClickLoginButtom_shouldDisplaydialog() {
        onView(withId(R.id.et_email_log)).perform(typeText("wp@gmail.com"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.string.string_fields_error_login)).check(matches(isDisplayed()));
    }

    @Test
    public void whenPasswordIsEmpty_andClickLoginButtom_shouldDisplayDialog(){
        onView(withId(R.id.et_senha)).perform(typeText("123456"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.string.string_fields_error_login)).check(matches(isDisplayed()));
    }

}

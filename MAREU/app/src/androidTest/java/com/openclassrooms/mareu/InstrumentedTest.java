package com.openclassrooms.mareu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TimePicker;


import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareu.util.DeleteChipViewAction;
import com.openclassrooms.mareu.util.DeleteViewAction;
import com.openclassrooms.mareu.view.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.mareu.util.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.contrib.RecyclerViewActions;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

// TEST MAIN ACTIVITY ______________________________________________________________________________
    @Test
    public void test_Check_ActivityMain_Display() {
        onView(withId(R.id.main_activity)).check(matches(isDisplayed()));
        onView(Matchers.allOf(withId(R.id.liste_recycler_view), isDisplayed())).check(matches(hasMinimumChildCount(0)));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), isDisplayed()));
    }

// TEST ADD ACTIVITY _______________________________________________________________________________
    @Test
    public void test_Click_AddMeeting() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.add_meeting_activity), isDisplayed()));
    }

    @Test
    public void test_Check_Add_Chip_Display_In_The_Chip_Group() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.add_meeting_activity), isDisplayed()));

        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(replaceText("gbjn@ghjk.gjh"), closeSoftKeyboard());
// click on send button on keyboard ime action
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(pressImeActionButton());
        onView(Matchers.allOf(withId(R.id.participant1), withText("gbjn@ghjk.gjh"), isDisplayed()));
    }

    @Test
    public void test_Remove_Chip_In_The_Chip_Group() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.add_meeting_activity), isDisplayed()));
// ADD MAIL ----------------------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(replaceText("gbjn@ghjk.gjh"), closeSoftKeyboard());
// click on send button on keyboard ime action
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(pressImeActionButton());
// ADD MAIL ----------------------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(replaceText("ukjyhf@gd.kljh"), closeSoftKeyboard());
// click on send button on keyboard ime action
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(pressImeActionButton());

        // verifier le contenue de edit text
        onView(Matchers.allOf(withId(R.id.list_participant), isDisplayed())).check(matches(hasChildCount(2)));

        onView(Matchers.allOf(withId(R.id.participant1), withText("gbjn@ghjk.gjh"), isDisplayed())).perform(new DeleteChipViewAction());

        onView(Matchers.allOf(withId(R.id.list_participant), isDisplayed())).check(matches(hasChildCount(1)));
    }

    @Test
    public void test_Validation_Error_Of_The_Meeting_With_Just_Name_Of_Input() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), childAtPosition(withId(R.id.main_activity), 1), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.edit_text_name_meeting), isDisplayed())).perform(replaceText("dfhj"), closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.add_meeting_activity), isDisplayed()));
    }

    @Test
    public void test_Validation_Error_Of_The_Meeting_With_Just_Spinner_Select() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), childAtPosition(withId(R.id.main_activity), 1), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.idSpinner), isDisplayed())).perform(click());

        onData(anything()).inAdapterView(withClassName(is("androidx.appcompat.widget.DropDownListView"))).atPosition(3).perform(click());
        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.add_meeting_activity), isDisplayed()));
    }

    @Test
    public void test_Validation_Error_Of_The_Meeting_With_Just_Date_Of_Input() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), childAtPosition(withId(R.id.main_activity), 1), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.date),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.add_meeting_activity), isDisplayed()));
    }

    @Test
    public void test_Validation_Error_Of_The_Meeting_With_Just_Hour_Of_Input() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), childAtPosition(withId(R.id.main_activity), 1), isDisplayed())).perform(click());
        onView(withId(R.id.hour)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(05,28));
        onView(withId(android.R.id.button1)).perform(click());
        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.add_meeting_activity), isDisplayed()));
    }

    @Test
    public void test_Validation_Error_Of_The_Meeting_With_Just_Mail_Of_Input() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), childAtPosition(withId(R.id.main_activity), 1), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(replaceText("gbjn@ghjk.gjh"), closeSoftKeyboard());
// click on send button on keyboard ime action
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(pressImeActionButton());

        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.add_meeting_activity), isDisplayed()));
    }

    @Test
    public void test_Validation_Error_Of_The_Meeting_With_All_The_Mandatory_Field_Entered() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), childAtPosition(withId(R.id.main_activity), 1), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.edit_text_name_meeting), isDisplayed())).perform(replaceText("dfhj"), closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.idSpinner), isDisplayed())).perform(click());
        onData(anything()).inAdapterView(withClassName(is("androidx.appcompat.widget.DropDownListView"))).atPosition(1).perform(click());
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(replaceText("gbjn@ghjk.gjh"), closeSoftKeyboard());
// click on send button on keyboard ime action
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(pressImeActionButton());

        onView(Matchers.allOf(withId(R.id.date),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.hour)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
    }

// TEST MAIN ACTIVITY ______________________________________________________________________________
    @Test
    public void test_The_Number_Of_Main_Activity_Items() {
// ADD MEETING -------------------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), childAtPosition(withId(R.id.main_activity), 1), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.edit_text_name_meeting), isDisplayed())).perform(replaceText("dfhj"), closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.idSpinner), isDisplayed())).perform(click());
        onData(anything()).inAdapterView(withClassName(is("androidx.appcompat.widget.DropDownListView"))).atPosition(2).perform(click());
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(replaceText("gbjn@ghjk.gjh"), closeSoftKeyboard());
// click on send button on keyboard ime action
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(pressImeActionButton());

        onView(Matchers.allOf(withId(R.id.date),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.hour)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
// COUNT ITEM  MEETING -----------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.liste_recycler_view), childAtPosition(withId(R.id.main_activity), 0), isDisplayed())).check(withItemCount(1));
    }

    @Test
    public void test_The_Delete_Of_Main_Activity_Items() {
// ADD MEETING -------------------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.edit_text_name_meeting), isDisplayed())).perform(replaceText("dfhj"), closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.idSpinner), isDisplayed())).perform(click());
        onData(anything()).inAdapterView(withClassName(is("androidx.appcompat.widget.DropDownListView"))).atPosition(2).perform(click());
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(replaceText("gbjn@ghjk.gjh"), closeSoftKeyboard());
        //clique sur bouton send sur clavier ime action
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(pressImeActionButton());

        onView(Matchers.allOf(withId(R.id.date),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.hour)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));

// DELETE MEETING ----------------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.liste_recycler_view), isDisplayed())).check(withItemCount(1));

        onView(allOf(withId(R.id.liste_recycler_view), isDisplayed()))
                .perform(closeSoftKeyboard())
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

        onView(Matchers.allOf(withId(R.id.liste_recycler_view), isDisplayed())).check(withItemCount(0));
    }

    @Test
    public void test_Main_Activity_Items_Data() {
// ADD MEETING -------------------------------------------------------------------------------------

        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.edit_text_name_meeting), isDisplayed())).perform(replaceText("dfhj"), closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.idSpinner), isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Réunion D"))).inRoot(isPlatformPopup()).perform(click());

        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(replaceText("gbjn@hjk.cm"), closeSoftKeyboard());
// click on send button on keyboard ime action
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(pressImeActionButton());

        onView(Matchers.allOf(withId(R.id.date),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.hour)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));

// STAT ITEM MEETING -------------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.liste_recycler_view), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()))
                .check(matches(hasDescendant(withText("Réunion D - " + "05h28" + " - dfhj"))));

        onView(Matchers.allOf(withId(R.id.liste_recycler_view), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()))
                .check(matches(hasDescendant(withText("gbjn@hjk.cm"))));
    }

// TEST ALERT DIALOG _______________________________________________________________________________
    @Test
    public void test_Alert_Dialog_Display_() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.menu_filtre), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.layout_filter), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.textdate), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.date_pickerr), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.spinner_fiter), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.button_clear), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.button_OK), isDisplayed())).perform(click());
    }

    @Test
    public void test_The_Clear_Alert_Dialog() {
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.menu_filtre), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.layout_filter), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.textdate), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.spinner_fiter), isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Réunion A"))).inRoot(isPlatformPopup()).perform(click());
        onView(Matchers.allOf(withId(R.id.spinner_fiter), isDisplayed())).check(matches(withSpinnerText(containsString("Réunion A"))));

        onView(Matchers.allOf(withId(R.id.button_clear), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.date_pickerr), isNotChecked()));
        onView(Matchers.allOf(withId(R.id.spinner_fiter), isDisplayed())).check(matches(withSpinnerText(containsString("SELECT ROOM"))));
    }

    @Test
    public void test_The_Validation_Alert_Dialog() {
// ADD MEETING -------------------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.floatingActionButton), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.edit_text_name_meeting), isDisplayed())).perform(replaceText("dfhj"), closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.idSpinner), isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(scrollTo(), click());

        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(replaceText("gbjn@hjk.cm"), closeSoftKeyboard());
// click on send button on keyboard ime action
        onView(Matchers.allOf(withId(R.id.get_mail), isDisplayed())).perform(pressImeActionButton());

        onView(Matchers.allOf(withId(R.id.date),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.hour)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(05,28));
        onView(withId(android.R.id.button1)).perform(click());

        onView(Matchers.allOf(withId(R.id.menu_valide), isDisplayed())).perform(click());

// SELECTION FILTRE --------------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.menu_filtre), isDisplayed())).perform(click());

        onView(Matchers.allOf(withId(R.id.layout_filter), isDisplayed()));

        onView(Matchers.allOf(withId(R.id.textdate),isDisplayed())).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,05,28));

        onView(Matchers.allOf(withId(R.id.spinner_fiter), isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Réunion A"))).inRoot(isPlatformPopup()).perform(click());


        onView(Matchers.allOf(withId(R.id.button_OK), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.main_activity), isDisplayed()));
//// STAT ITEM MEETING -------------------------------------------------------------------------------
        onView(Matchers.allOf(withId(R.id.liste_recycler_view), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()))
                .check(matches(hasDescendant(withText("Réunion A - " + "05h28" + " - dfhj"))));

        onView(Matchers.allOf(withId(R.id.liste_recycler_view), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()))
                .check(matches(hasDescendant(withText("gbjn@hjk.cm"))));
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}

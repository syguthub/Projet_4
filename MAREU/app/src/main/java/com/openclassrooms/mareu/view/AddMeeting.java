package com.openclassrooms.mareu.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.control.DateManagement;
import com.openclassrooms.mareu.control.HourManagement;
import com.openclassrooms.mareu.control.MailManagement;
import com.openclassrooms.mareu.control.SpinnerManagement;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.services.ApiService;

import static com.openclassrooms.mareu.services.SingleApiInstanceManagement.clear_Meeting_List;
import static com.openclassrooms.mareu.services.SingleApiInstanceManagement.get_Unique_Meeting_List;

/*
ACTIVITY ADD MEETING

*/
public class AddMeeting extends AppCompatActivity {

    public static final String mMessageError = "Enter the required fields";
    private TextInputLayout mNameMeeting;
    ApiService mApiService ;
    private MailManagement mailManagement;
    private DateManagement dateManagement;
    private HourManagement hourManagement;
    private SpinnerManagement spinnerManagement;

// CLEAR IF ORIENTATION CHANGE _____________________________________________________________________
    private int orientation;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        if (orientation != newConfig.orientation) {
            setContentView(R.layout.activity_add_meeting);
            all_Configuration();
            clear_Meeting_List();
        }
        orientation = newConfig.orientation;
    }

// CONSTRUCTOR _____________________________________________________________________________________
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        all_Configuration();
    }

// HIDE KEYBOARD ___________________________________________________________________________________
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null
                && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE)
                && view instanceof EditText
                && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

// CREATE RESOURCE MENU IN TOOLBAR _________________________________________________________________
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_meeting, menu);
        return true;
    }

// ALL CONFIGURATION _______________________________________________________________________________
    private void all_Configuration(){
        name_Meeting_Configuration();
        configuration_Of_Room_Selection();
        configuration_Of_Date_Selection();
        configuration_Of_Time_Selection();
        configuration_Of_mail_Selection();
    }

    // VIEW CONFIGURATION ______________________________________________________________________________
    private void name_Meeting_Configuration() {
        mNameMeeting = findViewById(R.id.name_meeting);
    }

//CONFIGURATION OF ROOM SELECTION __________________________________________________________________
    private void configuration_Of_Room_Selection() {
        spinnerManagement = new SpinnerManagement(this);
        spinnerManagement.room_Spinner_Configuration();
        spinnerManagement.select_Room();
    }

//CONFIGURATION OF DATE SELECTION __________________________________________________________________
    private void configuration_Of_Date_Selection() {
        dateManagement = new DateManagement(this);
        dateManagement.calendar_Listener_Configuration();
    }

//CONFIGURATION OF TIME SELECTION __________________________________________________________________
    private void configuration_Of_Time_Selection() {
        hourManagement = new HourManagement(this);
        hourManagement.timer_Configuration();
    }

//CONFIGURATION OF MAIL SELECTION __________________________________________________________________
    private void configuration_Of_mail_Selection() {
        mailManagement = new MailManagement(this);
        mailManagement.add_Mail_Button_Listener();
        mailManagement.visibility_Of_The_Icon_After_Error();
    }

// COMPULSORY INPUT CHECKS _________________________________________________________________________
    private boolean compulsory_Input_Checks() {

        boolean name = true;
        boolean room = spinnerManagement.error_Management();
        boolean participants = mailManagement.error_Management();

        if (mNameMeeting.getEditText().getText().toString().isEmpty()) {
            mNameMeeting.getEditText().setError(mMessageError);
            mNameMeeting.setEndIconVisible(false);
            name = false;
        }

        return name && participants && room;
    }

// VALID AND ADD MEETING ___________________________________________________________________________
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_valide) {
            if (compulsory_Input_Checks()) {
                Meeting mMeeting = new Meeting(
                        dateManagement.geomDateEntry().getText().toString(),
                        hourManagement.getmHourEntry().getText().toString(),
                        spinnerManagement.get_mSelect_Spinner(),
                        mNameMeeting.getEditText().getText().toString(),
                        mailManagement.convert_All_Mail_To_String()
                );
                mApiService=get_Unique_Meeting_List();
                mApiService.add_Meeting(mMeeting);
                finish();
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

// INITIALISE CURRENT DATE AND HOUR ________________________________________________________________
    @Override
    public void onResume() {
        current_Calendar();
        super.onResume();
    }

// GET CURRENT DATE AND HOUR _______________________________________________________________________
    private void current_Calendar(){
        dateManagement.set_Current_Date();
        hourManagement.set_Current_Hour();
    }

}

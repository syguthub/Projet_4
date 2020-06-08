package com.openclassrooms.mareu.control;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.openclassrooms.mareu.R;

import java.util.Calendar;

import static com.openclassrooms.mareu.services.UtilM.add_Zero_If_Number_Inferior_Ten;
import static com.openclassrooms.mareu.services.UtilM.current_Time;

/*
 CONFIGURATION TIME  WITCH TIMEPICKERDIALOG

*/
public class HourManagement {
    public TextView getmHourEntry() {
        return mHourEntry;
    }

    private String mHour;
    private Activity activity;
    private TextView mHourEntry;

// CONSTRUCTOR _____________________________________________________________________________________
    public HourManagement(Activity activity) {
        this.activity = activity;
        mHourEntry = activity.findViewById(R.id.hour);
        mHourEntry.setText(current_Time());
    }

    Calendar calendar;

// TIMEPICKERDIALOG CLICK CONFIGURATION ------------------------------------------------------------
    public void timer_Configuration() {
        calendar = Calendar.getInstance();
        mHourEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
// to add a 0 if the number is only one character --------------------------------------------------
                        mHour = add_Zero_If_Number_Inferior_Ten(hourOfDay) + "h" + add_Zero_If_Number_Inferior_Ten(minute);
                        mHourEntry.setText(mHour);
                    }
// INITIAL DISPLAY CURRENT TIME -------------------------------------------------------------------
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
        });
    }

// CLEAR HOUR ______________________________________________________________________________________
    public void set_Current_Hour() {
        mHourEntry.setText(current_Time());
    }

}

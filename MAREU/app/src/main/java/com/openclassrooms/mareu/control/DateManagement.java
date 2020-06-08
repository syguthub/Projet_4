package com.openclassrooms.mareu.control;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.openclassrooms.mareu.R;

import java.util.Calendar;

import static com.openclassrooms.mareu.services.UtilM.add_Zero_If_Number_Inferior_Ten;
import static com.openclassrooms.mareu.services.UtilM.current_Date;

/*
 CONFIGURATION DATE WITCH DATEPICKERDIALOG

*/
public class DateManagement {
    public TextView geomDateEntry() {
        return mDateEntry;
    }

    private String mDate;
    private Activity activity;
    private TextView mDateEntry;
    private Calendar calendar;
// CONSTRUCTOR _____________________________________________________________________________________
    public DateManagement(Activity activity) {
        this.activity = activity;
        mDateEntry = activity.findViewById(R.id.date);
        mDateEntry.setText(current_Date());
    }


    public void calendar_Listener_Configuration() {
// CONFIGURATION LISTENER CLICK DATEPICKERDIALOG ---------------------------------------------------
        mDateEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
// +1 FOR THE DISPLAY OF ME in EDIT TEXT CAR SURELY STORED IN A TABLE -------------------------------
// to add a 0 if the number is only one character --------------------------------------------------
                        mDate = add_Zero_If_Number_Inferior_Ten(dayOfMonth) + "/" +
                                add_Zero_If_Number_Inferior_Ten(month + 1) + "/" +
                                year;
                        mDateEntry.setText(mDate);
                    }
// DISPLAY INITIAL CURRENT DATE -------------------------------------------------------------------
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

// CLEAR DATE ______________________________________________________________________________________
    public void set_Current_Date() {
        mDateEntry.setText(current_Date());
    }
}

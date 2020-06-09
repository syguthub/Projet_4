package com.openclassrooms.mareu.control;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.core.graphics.drawable.DrawableCompat;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.control.adapter.SpinnerAdapter;

import static com.openclassrooms.mareu.services.Generate.get_List_Spinner;
import static com.openclassrooms.mareu.services.UtilM.set_Error_Drawable;
import static com.openclassrooms.mareu.services.UtilM.set_drawableBoardView_Drawable;
import static com.openclassrooms.mareu.view.MainActivity.mRoom;

/*
 CONFIGURATION CHOSE ROOM WITCH SPINNER

*/
public class SpinnerManagement {

    private Activity activity;


    private Spinner mSpinnerRoom;
    private String mSelectSpinner;

// CONSTRUCTOR _____________________________________________________________________________________
    public SpinnerManagement(Activity activity) {
        this.activity = activity;
        mSpinnerRoom = activity.findViewById(R.id.idSpinner);
    }

// CREATE SPINNER ADAPTER AND SET IN SPINNER _______________________________________________________
    public void room_Spinner_Configuration() {
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(activity, R.layout.item_spinner_head, get_List_Spinner(),false);
        mSpinnerRoom.setAdapter(spinnerAdapter);
        mSpinnerRoom.setBackground(set_drawableBoardView_Drawable(activity));
    }

// LISTEN TO THE SELECTION ON SPINNER SAVE SELECTION _______________________________________________
    public void select_Room() {
        mSpinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectSpinner = parent.getSelectedItem().toString();
                mSpinnerRoom.setBackground(set_drawableBoardView_Drawable(activity));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

// ERROR MANAGEMENT ________________________________________________________________________________
    public boolean error_Management() {
        if (mSelectSpinner.equals(mRoom)) {
// SET DRAWABLE IN TEXT VIEW -----------------------------------------------------------------------
            mSpinnerRoom.setBackground(set_Error_Drawable(activity));
            return false;
        }
        return true;
    }

// GETTER THE SELECTION OF SPINNER _________________________________________________________________
    public String get_mSelect_Spinner() {
        return mSelectSpinner;
    }

}

package com.openclassrooms.mareu.control;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.control.adapter.SpinnerAdapter;

import static com.openclassrooms.mareu.services.Generate.get_List_Spinner;
import static com.openclassrooms.mareu.services.UtilM.add_Zero_If_Number_Inferior_Ten;

/*
 CONFIGURATION FILTER ALERT DIALOG WITCH DIALOGFARGMENT

*/
public class FilterDialogAlert extends DialogFragment {

    private DatePicker mDatePicker;
    private Spinner mSpinnerFilter;
    private String mSelectRoom;
    private String mDate;
    private TextView textView;

    private Interface_AlertDialog interfaceAlertDialog;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            interfaceAlertDialog = (Interface_AlertDialog) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "onAttach in DonneAlertDialig");
        }
    }

// CREATE ALERT DIALOGUE ___________________________________________________________________________
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

// CREATE VIEW WITCH INFLATER AND SET IN BUILDER ---------------------------------------------------
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog_filter, null);
        builder.setView(view);

// VIEW CONFIGURATION ------------------------------------------------------------------------------
        textView = view.findViewById(R.id.textdate);
        Button mValidatedFilter = view.findViewById(R.id.button_OK);
        Button mCancelFilter = view.findViewById(R.id.button_clear);
        mDatePicker = view.findViewById(R.id.date_pickerr);
        mDatePicker.setVisibility(View.INVISIBLE);

// SPINNER CONFIGURATION ---------------------------------------------------------------------------
        mSpinnerFilter = view.findViewById(R.id.spinner_fiter);
        mSpinnerFilter.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, get_List_Spinner(),true));
// LISTENER ON THE VALIDATE BUTTON AND SEND DATA WITH THE INTERFACE AND STOP THE DIALOG ALERT ------
        mValidatedFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDatePicker.getVisibility()==View.VISIBLE) {
                    mDate = add_Zero_If_Number_Inferior_Ten(mDatePicker.getDayOfMonth()) + "/" +
                            add_Zero_If_Number_Inferior_Ten(mDatePicker.getMonth() + 1) + "/" +
                            add_Zero_If_Number_Inferior_Ten(mDatePicker.getYear());
                }else{
                    mDate="";
                }
                interfaceAlertDialog.data_Callback_filter(mDate, mSelectRoom);
                dismiss();
            }
        });

// LISTEN TO THE CLICK ON THE TEXT VIEW WITCH CHANGE WITH DATEPICKER ---------------------------------
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePicker.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }
        });

// LISTEN TO THE CLICK ON THE CANCEL BUTTON TO CLEAR THE FILTER ------------------------------------
        mCancelFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_The_Filter();
            }
        });

// LISTEN TO THE CLICK ON THE SPINNER TO SAVE SELECTION -----------------------------------------------
        mSpinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectRoom = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return builder.create();
    }

// CLEAR IF ORIENTATION CHANGE _____________________________________________________________________
    private int orientation;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (orientation != newConfig.orientation) {
            clear_The_Filter();
        }
        orientation = newConfig.orientation;
    }

// CLEAR ENTRY _____________________________________________________________________________________
    private void clear_The_Filter() {
        textView.setVisibility(View.VISIBLE);
        mSpinnerFilter.setSelection(0);
        mDatePicker.setVisibility(View.INVISIBLE);
    }

// SET DATA IN PARAMETER INTERFACE CALLBACK ________________________________________________________
    public interface Interface_AlertDialog {
        void data_Callback_filter(String date, String selectRoom);
    }
}



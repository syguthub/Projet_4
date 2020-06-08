package com.openclassrooms.mareu.control.adapter;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.openclassrooms.mareu.R;

public class SpinnerAdapter extends ArrayAdapter {
    boolean alertDialog;
// CONSTRUCTOR _____________________________________________________________________________________
    public SpinnerAdapter(@NonNull Activity activity, int resource, String[] listSalle,boolean alertDialog) {
        super(activity, resource, listSalle);
        this.alertDialog=alertDialog;
    }

// HEADER MANAGEMENT _______________________________________________________________________________
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_head, parent, false);
        TextView header=(TextView) view;
        if(alertDialog) {
            header.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        return super.getView(position, view, parent);
    }

// DROP DOWN MANAGEMENT ____________________________________________________________________________
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_dropdown, parent, false);
        TextView dropDown=(TextView) view;
        if(alertDialog) {
            dropDown.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        return super.getDropDownView(position, view, parent);
    }
}

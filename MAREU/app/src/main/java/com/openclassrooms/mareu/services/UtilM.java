package com.openclassrooms.mareu.services;


import android.app.Activity;
import android.graphics.drawable.Drawable;

import androidx.core.graphics.drawable.DrawableCompat;

import com.openclassrooms.mareu.R;

import java.util.Calendar;

/*
CLASS WITCH UTIL METHODS

*/
public class UtilM {

// ADD ZERO IF NUMBER INFERIOR TEN _________________________________________________________________
    public static String add_Zero_If_Number_Inferior_Ten(int number) {
        if (number < 10) {
            return "0" + Integer.toString(number);
        } else {
            return Integer.toString(number);
        }
    }

    private static Calendar calendar;

// GET CURRENT TIME IN FORMAT HH:MM ________________________________________________________________
    public static String current_Time() {
        calendar = Calendar.getInstance();
        return add_Zero_If_Number_Inferior_Ten(calendar.get(Calendar.HOUR_OF_DAY)) + "h" +
                add_Zero_If_Number_Inferior_Ten(calendar.get(Calendar.MINUTE));
    }

// GET CURRENT DATE IN FORMAT DD/MM/YYYY ___________________________________________________________
    public static String current_Date() {
        calendar = Calendar.getInstance();
        return add_Zero_If_Number_Inferior_Ten(calendar.get(Calendar.DAY_OF_MONTH)) + "/" +
                add_Zero_If_Number_Inferior_Ten(calendar.get(Calendar.MONTH) + 1) + "/" +
                calendar.get(Calendar.YEAR);
    }

// GET ERROR DRAWABLE ______________________________________________________________________________
    public static Drawable set_Error_Drawable(Activity activity) {
        Drawable drawableError;
        drawableError = DrawableCompat.wrap(activity.getResources().getDrawable(R.drawable.board_view_error));
        return drawableError;
    }

// GET ERROR DRAWABLE ______________________________________________________________________________
    public static Drawable set_drawableBoardView_Drawable(Activity activity) {
        Drawable drawableBoardView;
        drawableBoardView = DrawableCompat.wrap(activity.getResources().getDrawable(R.drawable.board_view));
        return drawableBoardView;
    }

}

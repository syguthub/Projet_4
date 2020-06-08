package com.openclassrooms.mareu.util;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;

import com.google.android.material.chip.Chip;
import com.openclassrooms.mareu.R;

import org.hamcrest.Matcher;

public class DeleteChipViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return ViewMatchers.isAssignableFrom(Chip.class);
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        Chip chip=view.findViewById(R.id.participant1);        // Maybe check for null
        chip.performCloseIconClick();
    }

//    override fun getConstraints(): Matcher<View> {
//        return ViewMatchers.isAssignableFrom(Chip::class.java)
//    }


}
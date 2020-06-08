package com.openclassrooms.mareu.control;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.mareu.R;

import java.util.Objects;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEND;
import static com.openclassrooms.mareu.services.UtilM.set_Error_Drawable;
import static com.openclassrooms.mareu.services.UtilM.set_drawableBoardView_Drawable;
import static com.openclassrooms.mareu.view.AddMeeting.mMessageError;

/*
 CONFIGURATION MAIL WITCH CHIP AND CHIPGROUP

*/
public class MailManagement {
    private Activity activity;
    private ScrollView scrollView;
    private ChipGroup mListChipGroup;
    private TextInputLayout mEntryMeetingParticipants;

// CONSTRUCTOR _____________________________________________________________________________________
    public MailManagement(Activity activity) {
        this.activity = activity;
// VIEW CONFIGURATION ------------------------------------------------------------------------------
        scrollView = activity.findViewById(R.id.scrollview);
        mListChipGroup = activity.findViewById(R.id.list_participant);
        mEntryMeetingParticipants = activity.findViewById(R.id.get_mail_layout);
        mEntryMeetingParticipants.getEditText().setText("");
        mEntryMeetingParticipants.setEndIconActivated(true);
        mEntryMeetingParticipants.setEndIconVisible(true);
        mEntryMeetingParticipants.setEndIconDrawable(R.drawable.ic_details_black_24dp);
    }

// ADD MAIL BUTTON LISTENER ________________________________________________________________________
    public void add_Mail_Button_Listener() {
// ADD MAIL WITCH KEYBOARD ------------------------------------------------------------------------
        mEntryMeetingParticipants.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == IME_ACTION_SEND) {
                    validate_the_Format_of_the_Email();
                    return true;
                } else {
                    return false;
                }
            }
        });
// ADD MAIL WITCH END ICON -------------------------------------------------------------------------
        mEntryMeetingParticipants.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate_the_Format_of_the_Email();
            }
        });
    }

// VALIDATE FORMAT MAIL OF THE EMAIL _______________________________________________________________
    private void validate_the_Format_of_the_Email() {
// GET CHAR SEQUENCE IN EDIT TEXT ------------------------------------------------------------------
        String[] tags = Objects.requireNonNull(mEntryMeetingParticipants.getEditText()).getText().toString().split(" ");
// IF THE EMAIL FORMAT IS VALID AND ENTRY ONLY ONE IS ENTERED, CREATE CHIP ELSE SET ERROR ----------
        if (Patterns.EMAIL_ADDRESS.matcher(tags[0]).matches()) {
            create_View_Chip(tags[0]);
            scrollView.setBackground(set_drawableBoardView_Drawable(activity));
        } else {
            if (tags.length > 1) {
                mEntryMeetingParticipants.setEndIconVisible(false);
                mEntryMeetingParticipants.getEditText().setError("( " + tags[0] + " ) " + "is not valid \n" + " and set one email");
            } else {
                mEntryMeetingParticipants.setEndIconVisible(false);
                mEntryMeetingParticipants.getEditText().setError("( " + tags[0] + " ) " + "is not valid ");
            }
        }
    }

// CREATE CHIP _____________________________________________________________________________________
    private void create_View_Chip(String tag) {
// CREATE CHIP VIEW --------------------------------------------------------------------------------
        LayoutInflater inflater = LayoutInflater.from(activity);
        final Chip chip = (Chip) inflater.inflate(R.layout.chip_item, null, false);
// CHIP TEXT CONFIGURATION -------------------------------------------------------------------------
        chip.setText(tag);
// REMOVE CHIP CONFIGURATION -----------------------------------------------------------------------
        remove_Chip(chip);
// ADD CHIP IN CHIP GROUP --------------------------------------------------------------------------
        mListChipGroup.addView(chip);
//CLEAR EDIT TEXT ----------------------------------------------------------------------------------
        mEntryMeetingParticipants.getEditText().setText("");
    }

// LISTENER REMOVE CHIP ____________________________________________________________________________
    private void remove_Chip(final Chip chip) {
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListChipGroup.removeView(chip);
            }
        });
    }

// CONVERT ALL CHIP IN CHIP GROUP TO ONE STRING ____________________________________________________
    public String convert_All_Mail_To_String() {
        String listParticipant = new String();

        for (int i = 0; i < mListChipGroup.getChildCount(); i++) {
            Chip chip = (Chip) mListChipGroup.getChildAt(i);
            if (i < mListChipGroup.getChildCount() - 1) {
                listParticipant += chip.getText().toString() + ", ";
            } else {
                listParticipant += chip.getText().toString();
            }
        }
        return listParticipant;
    }

// END ICON MANAGEMENT _____________________________________________________________________________
    public void visibility_Of_The_Icon_After_Error() {
        mEntryMeetingParticipants.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEntryMeetingParticipants.setEndIconVisible(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

// SET ERROR IF CHIP GROUP IS EMPTY_________________________________________________________________
    public boolean error_Management() {
        if (mListChipGroup.getChildCount() == 0) {
            mEntryMeetingParticipants . getEditText () . setError (mMessageError);
            mEntryMeetingParticipants.setEndIconVisible(false);
            scrollView.setBackground(set_Error_Drawable(activity));
            return false;
        }
        return true;
    }
}

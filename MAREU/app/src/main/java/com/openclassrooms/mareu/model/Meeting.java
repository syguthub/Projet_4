package com.openclassrooms.mareu.model;

import static com.openclassrooms.mareu.services.Generate.random_Color;

public class Meeting {

    private String mDate;
    private String mHour;
    private String mRoom;
    private String mName;
    private String mParticipants;
    private int color = random_Color();

    public Meeting(String mDate, String mHour, String mRoom, String mName, String mParticipants) {

        this.mDate = mDate;
        this.mHour = mHour;
        this.mRoom = mRoom;
        this.mName = mName;
        this.mParticipants = mParticipants;
    }

    public String getmDate() {
        return mDate;
    }

    public int getColor() {
        return color;
    }

    public String getmName() {
        return mName;
    }

    public String getmParticipants() {
        return mParticipants;
    }

    public String getmHour() {
        return mHour;
    }

    public String getmRoom() {
        return mRoom;
    }

}

package com.openclassrooms.mareu.event;

import com.openclassrooms.mareu.model.Meeting;

/*
TO RECOVER THE EVENT THEN THE MEETING STORED IN PUBLIC VARIABLES
GET MEETING FOR CLEAR

*/
public class EventDelete {
    public Meeting meeting;

    public EventDelete(Meeting meeting) {
        this.meeting = meeting;
    }
}

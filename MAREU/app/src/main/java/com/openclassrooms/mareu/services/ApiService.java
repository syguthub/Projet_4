package com.openclassrooms.mareu.services;

import com.openclassrooms.mareu.model.Meeting;

import java.util.List;

/*
MANAGEMENT INTERFACE FOR MEETING LIST

 */
public interface ApiService {

    void add_Meeting(Meeting meeting);

    void delete_Meeting(Meeting meeting);

    List<Meeting> get_Meeting_List_Filter(String date, String room);

    List<Meeting> get_Meeting_List();
}

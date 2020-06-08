package com.openclassrooms.mareu.services;

import com.openclassrooms.mareu.model.Meeting;

import java.util.List;

/*
MANAGEMENT INTERFACE FOR MEETING LIST

 */
public interface ApiService {

    public void add_Meeting(Meeting meeting);

    public void delete_Meeting(Meeting meeting);

    public List<Meeting> get_Meeting_List_Filter(String date, String room);

    public List<Meeting> get_Meeting_List();
}

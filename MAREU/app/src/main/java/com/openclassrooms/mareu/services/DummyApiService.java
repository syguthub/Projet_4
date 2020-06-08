package com.openclassrooms.mareu.services;

import com.openclassrooms.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/*
 CLASS D'INSTANCIATION FOR MANAGEMENT INTERFACE

 */
public class DummyApiService implements ApiService {

    private List<Meeting> mList = new ArrayList<>();

    @Override
    public void add_Meeting(Meeting meeting) {
        mList.add(meeting);
    }

    @Override
    public void delete_Meeting(Meeting meeting) {
        mList.remove(meeting);
    }

    @Override
    public List<Meeting> get_Meeting_List() {
        return mList;
    }

    @Override
    public List<Meeting> get_Meeting_List_Filter(String date, String room) {
        List<Meeting> mByFilter = new ArrayList();
        for (int i = 0; i < mList.size(); i++) {
            if (!date.equals("null") && !(room.equals("SELECT ROOM"))) {
                if ((mList.get(i).getmDate().equals(date)) &&
                        mList.get(i).getmRoom().equals(room)) {
                    mByFilter.add(mList.get(i));
                }
            } else if (date.equals("null") && !(room.equals("SELECT ROOM")) ) {
                if (mList.get(i).getmRoom().equals(room)) {
                    mByFilter.add(mList.get(i));
                }
            } else if (!date.equals("null") && room.equals("SELECT ROOM")) {
                if ((mList.get(i).getmDate().equals(date))) {
                    mByFilter.add(mList.get(i));
                }
            } else if (date.equals("null") && room.equals("SELECT ROOM")) {
                mByFilter.add(mList.get(i));
            }
        }
        return mByFilter;
    }
}

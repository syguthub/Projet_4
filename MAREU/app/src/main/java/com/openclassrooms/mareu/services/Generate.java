package com.openclassrooms.mareu.services;

import android.graphics.Color;

import com.openclassrooms.mareu.model.Meeting;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
/*
GENERATE DATA

*/

public class Generate {

// GENERATE A RANDOM COLOR FOR IMAGEVIEW OF THE RECYCLERVIEW ITEM __________________________________
    public static int random_Color() {
        List<Integer> colorList = Arrays.asList(
                Color.MAGENTA,
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.CYAN,
                Color.YELLOW,
                Color.GRAY,
                Color.LTGRAY
        );
        Random random = new Random();
        return colorList.get(random.nextInt(colorList.size()));
    }

// GENERATE THE LIST OF THE ROOM SELECTION SPINNER _________________________________________________
    public static String[] get_List_Spinner() {
        return new String[]{
                "SELECT ROOM",
                "Réunion A",
                "Réunion B",
                "Réunion C",
                "Réunion D",
                "Réunion E",
                "Réunion F",
                "Réunion G",
                "Réunion H",
                "Réunion I",
                "Réunion J"
        };
    }

// GENERATE A MEETING LIST FOR TESTS _______________________________________________________________
    public static List<Meeting> get_List_Meeting_For_Test() {
        return Arrays.asList(new Meeting("21/1/1980", "11:11", "réunion A", "SUJET1", "GJKHVCJHVCNHG GYFVGHFNGH GKUYFJYTFV HYUG?JHV G"),
                new Meeting("27/6/2100", "11:11", "réunion B", "SUJET2", "GJKHVCJHVCNHG GYFVGHFNGH GKUYFJYTFV HYUG?JHV G"),
                new Meeting("15/12/2080", "11:11", "réunion A", "SUJET3", "GJKHVCGGJKHVCJHVCNHG GYFVGHFNGH GKUYFJYTFV HYUG?JHV G"),
                new Meeting("3/11/2045", "11:11", "réunion F", "SUJET4", "GJKHVCGJKHVCJHVCNHG GYFVGHFNGH GKUYFJYTFV HYUG?JHV GG"),
                new Meeting("18/7/1989", "11:11", "réunion C", "SUJET5", "GJKHVGJKHVCJHVCNHG GYFVGHFNGH GKUYFJYTFV HYUG?JHV GCG"),
                new Meeting("11/4/1923", "11:11", "réunion A", "SUJET6", "GJKHVCGJKHVCJHVCNHG GYFVGHFNGH GKUYFJYTFV HYUG?JHV GG"),
                new Meeting("27/6/2100", "11:11", "réunion H", "SUJET7", "GGJKHVCJHVCNHG GYFVGHFNGH GKUYFJYTFV HYUG?JHV GJKHVCG")
        );
    }

// GENERATE A RANDOM MEETING FROM THE MEETING LIST FOR TESTS _______________________________________
    public static Meeting get_Random_Meeting(List<Meeting> meetingForTest) {
        Random random = new Random();
        return meetingForTest.get(random.nextInt(meetingForTest.size()));
    }
}

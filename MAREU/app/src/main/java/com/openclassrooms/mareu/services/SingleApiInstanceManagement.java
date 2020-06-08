package com.openclassrooms.mareu.services;

public class SingleApiInstanceManagement {

    public static ApiService apiService = new DummyApiService();

// GET A LIST WITH A SINGLE REFERENCE ______________________________________________________________
    public static ApiService get_Unique_Meeting_List() {
        return apiService;
    }

// GET A NEW LIST __________________________________________________________________________________
    public static void clear_Meeting_List() {
        apiService.get_Meeting_List().clear();
    }

// GET A instance __________________________________________________________________________________
    public static void new_Meeting_List() {
        apiService = new DummyApiService();
    }

}


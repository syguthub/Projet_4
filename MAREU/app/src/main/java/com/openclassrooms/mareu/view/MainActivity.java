package com.openclassrooms.mareu.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.control.FilterDialogAlert;
import com.openclassrooms.mareu.control.adapter.RecyclerViewAdapter;
import com.openclassrooms.mareu.event.EventDelete;
import com.openclassrooms.mareu.services.ApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.openclassrooms.mareu.services.SingleApiInstanceManagement.apiService;
import static com.openclassrooms.mareu.services.SingleApiInstanceManagement.get_Unique_Meeting_List;
import static com.openclassrooms.mareu.services.SingleApiInstanceManagement.clear_Meeting_List;

/*
START-UP ACTIVITY WITCH LIST MEETING

*/

public class MainActivity
            extends AppCompatActivity
                    implements FilterDialogAlert.Interface_AlertDialog {

    private static final String mTitle = "Ma réu";
    public static final String mRoom = "SELECT ROOM";
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ApiService mApiService;

// CLEAR IF ORIENTATION CHANGES ____________________________________________________________________
    private int orientation=0;
    private ApiService beforeApiService =apiService;

//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if (orientation != newConfig.orientation) {
////IF THE API CHANGES, THERE IS A ROTATION IN ADD MEETING, SO A NEW INSTANCE THE API HAS BEEN CREATED
//// RESET THE RECYCLER.
//// ELSE DELETE THE API LIST
//            if(beforeApiService != apiService){
//                beforeApiService =apiService;
//                reset_RecyclerView_And_Adapter();
//            }else {
//                clear_Meeting_List();
//                reset_RecyclerView_And_Adapter();
//            }
//            orientation = newConfig.orientation;
//        }
//    }

// RESET RECYCLERVIEW ______________________________________________________________________________
    private void reset_RecyclerView_And_Adapter(){
        mApiService = get_Unique_Meeting_List();
        mRecyclerViewAdapter = new RecyclerViewAdapter(mApiService.get_Meeting_List());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

// CONSTRUCTOR _____________________________________________________________________________________
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(mTitle);

        start_Add_Meeting_Activity();
        recyclerView_Configuration();

        clear_Meeting_List();
        reset_RecyclerView_And_Adapter();
    }

// CREATE RESOURCE MENU IN TOOLBAR _________________________________________________________________
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

// START ALERT DIALOG ______________________________________________________________________________
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_filtre) {
            FilterDialogAlert filterDialogAlert = new FilterDialogAlert();
            filterDialogAlert.show(getSupportFragmentManager(), null);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

// START ADD MEETING _______________________________________________________________________________
    private void start_Add_Meeting_Activity() {
        FloatingActionButton mAddMeetingButton = findViewById(R.id.floatingActionButton);
        mAddMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMeeting.class);
                startActivity(intent);
            }
        });
    }

// CONFIGURATION RECYCLERVIEW ______________________________________________________________________
    private void recyclerView_Configuration() {
        mRecyclerView = findViewById(R.id.liste_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mApiService = get_Unique_Meeting_List();
        mRecyclerViewAdapter = new RecyclerViewAdapter(mApiService.get_Meeting_List());
    }

// GET DATA IN PARAMETER INTERFACE CALLBACK FOR INITIALISE RECYCLERVIEW ____________________________
    @Override
    public void data_Callback_filter(String date, String selectRoom) {

        mRecyclerView.setAdapter(new RecyclerViewAdapter(
                mApiService.get_Meeting_List_Filter(date, selectRoom)));
    }

// GET DATA IN PARAMETER WITCH EVENT CALLBACK FOR DELETE MEETING ___________________________________
    @Subscribe
    public void delete(EventDelete eventDelete) {
        apiService.delete_Meeting(eventDelete.meeting);
        reset_RecyclerView_And_Adapter();
    }

// START EVENT _____________________________________________________________________________________
    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

// STOP EVENT ______________________________________________________________________________________
    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

// INITIALISE RECYCLERVIEW _________________________________________________________________________
    @Override
    public void onResume() {
        reset_RecyclerView_And_Adapter();
        super.onResume();
    }

}

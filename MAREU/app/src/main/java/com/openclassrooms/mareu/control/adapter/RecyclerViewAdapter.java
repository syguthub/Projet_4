package com.openclassrooms.mareu.control.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.event.EventDelete;
import com.openclassrooms.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;


import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
// CONSTRUCTOR _____________________________________________________________________________________
    public RecyclerViewAdapter(List<Meeting> mList) {
        this.mList = mList;
    }

    private List<Meeting> mList;

// CREATE VIEW _____________________________________________________________________________________
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting_recyclerview, parent, false);
        return new ViewHolder(view);
    }

// CONFIGURATION DATA ITEM RECYCLERVIEW ____________________________________________________________
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        String titreItem = mList.get(position).getmRoom() + " - " +
                mList.get(position).getmHour().substring(0, 2) + "h" +
                mList.get(position).getmHour().substring(3, 5) + " - " +
                mList.get(position).getmName();

        viewHolder.mTitle.setText(titreItem);

        viewHolder.mColor.setColorFilter(mList.get(position).getColor());

        viewHolder.mMeetingParticipants.setText(mList.get(position).getmParticipants());

        viewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// EVENT CALLBACK SET DATA IN PARAMETER FOR DELETE MEETING -----------------------------------------
                EventBus.getDefault().post(new EventDelete(mList.get(position)));
            }
        });
    }

// ITEM COUNT RECYCLERVIEW _________________________________________________________________________
    @Override
    public int getItemCount() {
        return mList.size();
    }

// CONFIGURATION VIEW ______________________________________________________________________________

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mMeetingParticipants;
        private ImageView mDelete;
        private ImageView mColor;

        public ViewHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.title);
            mMeetingParticipants = view.findViewById(R.id.state);
            mDelete = view.findViewById(R.id.delete);
            mColor = view.findViewById(R.id.couleur);
        }
    }

}

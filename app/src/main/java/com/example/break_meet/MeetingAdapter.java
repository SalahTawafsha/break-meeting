package com.example.break_meet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private final Meeting[] requests;
    private boolean isSelectMeeting;

    public MeetingAdapter(List<Meeting> requests, boolean isSelectMeeting) {
        this.requests = requests.toArray(new Meeting[0]);
        this.isSelectMeeting = isSelectMeeting;
    }

    @NonNull
    @Override
    public MeetingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_card,
                parent,
                false);

        return new MeetingAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MeetingAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView studentID = cardView.findViewById(R.id.meetingStudentIdd);
        if (isSelectMeeting)
            studentID.setText(requests[position].getStudentId());
        else
            studentID.setVisibility(View.GONE);

        TextView placeName = cardView.findViewById(R.id.meetingPlace);
        placeName.setText(requests[position].getPlaceName());

        TextView type = cardView.findViewById(R.id.type);
        type.setText(requests[position].getType());

        TextView description = cardView.findViewById(R.id.des);
        String s = "On: " + requests[position].getDate() + ", " + requests[position].getFromTime()
                + ". " + requests[position].getDes();
        description.setText(s);


        cardView.setOnClickListener(v -> {
            if (isSelectMeeting) {
                SelectMeetingActivity.setIndex(holder.getAdapterPosition());
                SelectMeetingActivity.getSelect().setEnabled(true);
            } else {
                MyMeetingsActivity.setIndex(holder.getAdapterPosition());
                MyMeetingsActivity.getDelete().setEnabled(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requests.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

    }
}
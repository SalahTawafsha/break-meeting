package com.example.break_meet;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private final Meeting[] requests;

    public RequestAdapter(ArrayList<Meeting> requests) {
        this.requests = requests.toArray(new Meeting[0]);
    }

    @NonNull
    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.request_card,
                parent,
                false);

        return new RequestAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RequestAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView studentID = cardView.findViewById(R.id.studentIdd);
        studentID.setText(requests[position].getStudentId());

        TextView placeName = cardView.findViewById(R.id.place);
        placeName.setText(requests[position].getPlaceName());

        TextView time = cardView.findViewById(R.id.requestTime);
        time.setText(requests[position].getStringDate());


        cardView.setOnClickListener(v -> {
            RequestToMeActivity.setMeetingID(RequestToMeActivity.getKeys().get(position));
            RequestToMeActivity.setSelectedIndex(holder.getAdapterPosition());
            RequestToMeActivity.getApproval().setEnabled(true);
            RequestToMeActivity.getReject().setEnabled(true);
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
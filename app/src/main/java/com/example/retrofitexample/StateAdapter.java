package com.example.retrofitexample;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateViewHolder>{

    private List<RegionalData> stateList;

    public StateAdapter(List<RegionalData> tempList) {
        stateList = new ArrayList<>(tempList);
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_state, parent,
                false);
        return new StateViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {
        RegionalData regionalData = stateList.get(position);
        holder.textViewState.setText(regionalData.getStateName());
        holder.textViewTotal.setText("Total Cases = " + regionalData.getTotalConfirmed());
        holder.textViewDeath.setText("Number of Deaths = " + regionalData.getDeaths());
        holder.textViewRecovered.setText("Recovered Cases = " + regionalData.getDischarged());
        holder.textViewActive.setText("Active Cases = " +
                (regionalData.getTotalConfirmed() - regionalData.getDischarged() - regionalData.getDeaths()));
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTotal;
        private TextView textViewRecovered;
        private TextView textViewDeath;
        private TextView textViewActive;
        private TextView textViewState;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTotal = itemView.findViewById(R.id.textViewTotalCases);
            textViewRecovered = itemView.findViewById(R.id.textViewRecoveredCases);
            textViewDeath = itemView.findViewById(R.id.textViewDeath);
            textViewActive = itemView.findViewById(R.id.textViewActiveCases);
            textViewState = itemView.findViewById(R.id.textViewState);
        }
    }
}

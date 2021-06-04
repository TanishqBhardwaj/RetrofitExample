package com.example.retrofitexample.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample.R;
import com.example.retrofitexample.model.CountriesModel;
import com.example.retrofitexample.model.RegionalData;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.StateViewHolder>{

    private List<RegionalData> stateList;
    private List<CountriesModel> countriesList;
    private OnItemClickListener mListener;
    private boolean isGlobal;

    public DetailAdapter(List<RegionalData> tempList) {
        stateList = new ArrayList<>(tempList);
        isGlobal = false;
    }

    public DetailAdapter(List<CountriesModel> countriesModelList, boolean isGlobal) {
        countriesList = new ArrayList<>(countriesModelList);
        this.isGlobal = isGlobal;
    }

    public interface OnItemClickListener {
        void onItemClick();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_detail, parent,
                false);
        return new StateViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {
        if(isGlobal) {
            CountriesModel countriesModel = countriesList.get(position);
            holder.textViewName.setText(countriesModel.getCountryName());
            holder.textViewTotal.setText(String.valueOf(countriesModel.getTotalConfirmedCases()));
            holder.textViewDeath.setText(String.valueOf(countriesModel.getTotalDeathsCases()));
            holder.textViewRecovered.setText(String.valueOf(countriesModel.getTotalRecoveredCases()));
            holder.textViewActive.setText(
                    String.valueOf(countriesModel.getTotalConfirmedCases()
                            - countriesModel.getTotalRecoveredCases()
                            - countriesModel.getTotalDeathsCases()));

            holder.textViewTotalNew.setText(String.valueOf(countriesModel.getTotalConfirmedCases()));
            holder.textViewRecoveredNew.setText(String.valueOf(countriesModel.getNewRecoveredCases()));
            holder.textViewDeathNew.setText(String.valueOf(countriesModel.getNewDeathsCases()));
        }
        else {
            RegionalData regionalData = stateList.get(position);
            holder.textViewName.setText(regionalData.getStateName());
            holder.textViewTotal.setText(String.valueOf(regionalData.getTotalConfirmed()));
            holder.textViewDeath.setText(String.valueOf(regionalData.getDeaths()));
            holder.textViewRecovered.setText(String.valueOf(regionalData.getDischarged()));
            holder.textViewActive.setText(
                    String.valueOf(regionalData.getTotalConfirmed() - regionalData.getDischarged() - regionalData.getDeaths()));

            holder.constraintLayoutTotalNew.setVisibility(View.GONE);
            holder.constraintLayoutRecoveredNew.setVisibility(View.GONE);
            holder.constraintLayoutDeathNew.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if(isGlobal) {
            return countriesList.size();
        }
        return stateList.size();
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTotal;
        private TextView textViewRecovered;
        private TextView textViewDeath;
        private TextView textViewActive;
        private TextView textViewName;

        private TextView textViewTotalNew;
        private TextView textViewRecoveredNew;
        private TextView textViewDeathNew;
        private ConstraintLayout constraintLayoutTotalNew;
        private ConstraintLayout constraintLayoutRecoveredNew;
        private ConstraintLayout constraintLayoutDeathNew;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTotal = itemView.findViewById(R.id.textViewTotalCases);
            textViewRecovered = itemView.findViewById(R.id.textViewRecoveredCases);
            textViewDeath = itemView.findViewById(R.id.textViewDeath);
            textViewActive = itemView.findViewById(R.id.textViewActiveCases);
            textViewName = itemView.findViewById(R.id.textViewName);

            textViewTotalNew = itemView.findViewById(R.id.text_view_total_new);
            textViewRecoveredNew = itemView.findViewById(R.id.text_view_recovered_new);
            textViewDeathNew = itemView.findViewById(R.id.text_view_death_new);
            constraintLayoutTotalNew = itemView.findViewById(R.id.constraintLayoutTotalNew);
            constraintLayoutRecoveredNew = itemView.findViewById(R.id.constraintLayoutRecoveredNew);
            constraintLayoutDeathNew = itemView.findViewById(R.id.constraintLayoutDeathNew);

            itemView.setOnClickListener(view -> {
                if(mListener!=null) {
                    int pos =getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        mListener.onItemClick();
                    }
                }
            });
        }
    }
}

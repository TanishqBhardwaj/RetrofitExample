package com.example.retrofitexample.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample.R;
import com.example.retrofitexample.model.CountriesModel;
import com.example.retrofitexample.model.RegionalData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Top5Adapter extends RecyclerView.Adapter<Top5Adapter.Top5ViewHolder>{

    private List<RegionalData> stateList;
    private List<CountriesModel> countriesList;
    private List<RegionalData> tempStateList;
    private List<CountriesModel> tempCountriesList;
    private boolean isGlobal;

    public Top5Adapter(List<RegionalData> tempList) {
        stateList = new ArrayList<>(tempList);
        tempStateList = new ArrayList<>(tempList);
        isGlobal = false;
    }

    public Top5Adapter(List<CountriesModel> countriesModelList, boolean isGlobal) {
        countriesList = new ArrayList<>(countriesModelList);
        tempCountriesList = new ArrayList<>(countriesModelList);
        this.isGlobal = isGlobal;
    }

    @NonNull
    @Override
    public Top5ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_top_item, parent,
                false);
        return new Top5ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Top5ViewHolder holder, int position) {
        if(isGlobal) {
            Collections.sort(tempCountriesList, CountriesModel.countriesModelComparator);
            holder.textViewName.setText(tempCountriesList.get(position).getCountryName());
            holder.textViewActive.setText(String.valueOf(
                    tempCountriesList.get(position).getTotalConfirmedCases()
                            - tempCountriesList.get(position).getTotalRecoveredCases()
                            - tempCountriesList.get(position).getTotalDeathsCases()));
            holder.textViewRecovered.setText(String.valueOf(
                    tempCountriesList.get(position).getTotalRecoveredCases()));
            holder.textViewDeath.setText(String.valueOf(
                    tempCountriesList.get(position).getTotalDeathsCases()));
        }
        else {
            Collections.sort(tempStateList, RegionalData.regionalDataComparator);
            for(RegionalData regionalData : tempStateList) {
                Log.e("TAG", "onBindViewHolder: " + regionalData.getConfirmedCasesIndian());
            }
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class Top5ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewActive;
        private TextView textViewRecovered;
        private TextView textViewDeath;

        public Top5ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewNameCard);
            textViewActive = itemView.findViewById(R.id.textViewActive);
            textViewRecovered = itemView.findViewById(R.id.textViewRecovered);
            textViewDeath = itemView.findViewById(R.id.textViewDeathCard);
        }
    }
}

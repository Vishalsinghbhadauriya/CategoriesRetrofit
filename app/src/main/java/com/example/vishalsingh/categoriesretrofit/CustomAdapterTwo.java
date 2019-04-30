package com.example.vishalsingh.categoriesretrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishalsingh.categoriesretrofit.model.Category;
import com.example.vishalsingh.categoriesretrofit.model.Company;

import java.util.List;

public class CustomAdapterTwo extends RecyclerView.Adapter<CustomAdapterTwo.CustomViewHolderTwo>{

    private List<Company> dataList;
    private Context context;

    public CustomAdapterTwo(Context context, List<Company> dataList){
        this.context = context;
        this.dataList = dataList;
    }
    class CustomViewHolderTwo extends RecyclerView.ViewHolder {
        TextView categoryid,company_name,company_id;

        CustomViewHolderTwo(View itemView) {
            super(itemView);
            categoryid=(TextView)itemView.findViewById(R.id.categoryid);
            company_name=(TextView)itemView.findViewById(R.id.company_name);
            company_id=(TextView)itemView.findViewById(R.id.company_id);

        }
    }
    @NonNull
    @Override
    public CustomViewHolderTwo onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.companyadapter, parent, false);
        return new CustomAdapterTwo.CustomViewHolderTwo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolderTwo customViewHolderTwo, int i) {
        customViewHolderTwo.categoryid.setText(dataList.get(i).getCategoryid());
        customViewHolderTwo.company_name.setText(dataList.get(i).getName());
        customViewHolderTwo.company_id.setText(dataList.get(i).getCompanyId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }




}

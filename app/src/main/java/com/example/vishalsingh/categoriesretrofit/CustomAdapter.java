package com.example.vishalsingh.categoriesretrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishalsingh.categoriesretrofit.model.Category;
import com.example.vishalsingh.categoriesretrofit.model.Example;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Category> dataList;
    private Context context;

    public CustomAdapter(Context context,List<Category> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView txtid,txtname;
        ImageView image;
        CustomViewHolder(View itemView) {
            super(itemView);
            txtid=(TextView)itemView.findViewById(R.id.id);
            txtname=(TextView)itemView.findViewById(R.id.name);
            image=(ImageView)itemView.findViewById(R.id.image_icon);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categriesadapter, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.txtid.setText(dataList.get(position).getId());
        holder.txtname.setText(dataList.get(position).getName());
        Picasso.with(context).load(dataList.get(position).getIcon()).into(holder.image);

      /*  Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getIcon())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);*/

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

package com.crevator.lagosdev.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;


import com.crevator.lagosdev.Model.Developer;
import com.crevator.lagosdev.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.MyViewHolder> {

    private List<Developer> mDeveloperList;
    private List<Developer> mFilteredDeveloperList;
    Activity context;
    Filter filter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        ImageView pic;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            pic = (ImageView) view.findViewById(R.id.pic);
        }
    }


    public DevelopersAdapter(List<Developer> developerList, Activity context) {
        this.mDeveloperList = developerList;
        this.mFilteredDeveloperList = developerList;
        this.context = context;
        setFilter();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_developers, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Developer developer = mFilteredDeveloperList.get(position);
        holder.name.setText(developer.getUserName());
        //handle image loading using picasso
        Picasso.with(context).load(developer.getImgURL()).placeholder(R.drawable.round).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        try {
            return mFilteredDeveloperList.size();
        } catch (Exception e) {
            return 0;
        }

    }


    public void filterList(String text) {
        filter.filter(text);
    }

    private void setFilter() {
        filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<Developer> newFilters = new ArrayList<>();
                FilterResults results = new FilterResults();
                for (Developer developer : mDeveloperList) {
                    if (developer.getUserName().toLowerCase().trim().contains(constraint)) {
                        newFilters.add(developer);
                    }
                }
                results.values = newFilters;
                results.count = newFilters.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredDeveloperList = (ArrayList<Developer>) results.values;
                DevelopersAdapter.this.notifyDataSetChanged();
            }
        };
    }

}

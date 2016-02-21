package com.example.will.Playground;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Will on 21/02/2016.
 */
public class NavigationRecyclerViewAdapter extends RecyclerView.Adapter<NavigationRecyclerViewAdapter.NavRecyclerViewHolder> {

    List<String> mNavEntries;

    public NavigationRecyclerViewAdapter(List<String> mNavEntries) {
        this.mNavEntries = mNavEntries;
    }

    @Override
    public NavRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View navEntry = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_recycler_view_item, parent, false);
        return new NavRecyclerViewHolder(navEntry);
    }

    @Override
    public void onBindViewHolder(NavRecyclerViewHolder holder, int position) {
        holder.bindView(mNavEntries.get(position));
    }

    @Override
    public int getItemCount() {
        return mNavEntries.size();
    }

    public static class NavRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mNavigationTitle;

        public NavRecyclerViewHolder(View itemView) {
            super(itemView);
            this.mNavigationTitle = (TextView) itemView.findViewById(R.id.navigation_recycler_view_item_text);
        }

        public void bindView(String navTitle) {
            mNavigationTitle.setText(navTitle);
        }
    }
}

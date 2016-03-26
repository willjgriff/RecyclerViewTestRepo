package com.github.willjgriff.playground.navigation;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.UiUtils;

import java.util.List;

/**
 * Created by Will on 21/02/2016.
 */
public class NavigationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NavigationEntry> mNavEntries;
    private int mSelectedPosition;

    enum ItemType {
        ITEM,
        HEADER
    }

    public NavigationRecyclerViewAdapter(List<NavigationEntry> navEntries) {
        mNavEntries = navEntries;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == ItemType.HEADER.ordinal()) {
            View header = layoutInflater.inflate(R.layout.adapter_navigation_header, parent, false);
            ViewCompat.setElevation(header, UiUtils.convertDpToPixel(4, parent.getContext()));
            viewHolder = new NavHeaderViewHolder(header);
        } else if (viewType == ItemType.ITEM.ordinal()) {
            View navEntry = layoutInflater.inflate(R.layout.adapter_navigation_item, parent, false);
            viewHolder = new NavRecyclerViewHolder(navEntry);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NavHeaderViewHolder) {
            // If I want to populate the header view
        } else if (holder instanceof NavRecyclerViewHolder) {
            ((NavRecyclerViewHolder) holder).bindView(getItem(position).getNavigationTitle(), getItem(position).getNavigationClickListener());
        }
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = ItemType.ITEM.ordinal();
        if (isHeaderPosition(position)) {
            itemType = ItemType.HEADER.ordinal();
        }
        return itemType;
    }

    private boolean isHeaderPosition(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return mNavEntries.size() + 1;
    }

    private NavigationEntry getItem(int position) {
        return mNavEntries.get(position - 1);
    }

    public void setSelectedPosition(int position) {
        mSelectedPosition = position + 1;
        notifyDataSetChanged();
    }

    public class NavHeaderViewHolder extends RecyclerView.ViewHolder {
        public NavHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class NavRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView mNavigationTitle;
        private LinearLayout mNavigationLayout;

        Context mContext;
        public NavRecyclerViewHolder(View itemView) {
            super(itemView);
            mNavigationTitle = (TextView) itemView.findViewById(R.id.navigation_recycler_view_item_text);
            mNavigationLayout = (LinearLayout) itemView.findViewById(R.id.navigation_recycler_view_item_layout);
            mContext = itemView.getContext();
        }

        public void bindView(String navTitle, View.OnClickListener navClickListener) {
            mNavigationTitle.setText(navTitle);
            mNavigationLayout.setOnClickListener(navClickListener);
            if (getAdapterPosition() == mSelectedPosition) {
                mNavigationLayout.setSelected(true);
            } else {
                mNavigationLayout.setSelected(false);
            }
        }
    }
}

package com.github.willjgriff.playground.navigation;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.utils.UiUtils;

import java.util.List;

/**
 * Created by Will on 21/02/2016.
 */
public class NavigationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_ITEMS = 1;
    private List<NavigationEntry> mNavEntries;
    private int mSelectedPosition;

    // Ideally I would create multiple data types for each ItemType and this Adapter would hold
    // a list of those data types. Also, could use enumerated annotations instead of enum...
    private enum ItemType {
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
            View header = layoutInflater.inflate(R.layout.view_navigation_header_item, parent, false);
            viewHolder = new NavHeaderViewHolder(header);
        } else if (viewType == ItemType.ITEM.ordinal()) {
            View navEntry = layoutInflater.inflate(R.layout.view_navigation_item, parent, false);
            viewHolder = new NavRecyclerViewHolder(navEntry);
        }

        return viewHolder;
    }

    // TODO: There must be a way to avoid reflection here...
    // If the data list (mNavEntries) is generic and contains the header then the specific data item can include it's
    // type. Then we can just bind the data at mNavEntries(position) to a generic ViewHolder with a bind method.
    // Overkill here just to get rid of reflection and slightly confusing item counts, will do in PeopleRecyclerViewAdapter.
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
        if (isHeaderPosition(position)) {
            return ItemType.HEADER.ordinal();
        } else {
            return ItemType.ITEM.ordinal();
        }
    }

    private boolean isHeaderPosition(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return mNavEntries.size() + HEADER_ITEMS;
    }

    private NavigationEntry getItem(int position) {
        return mNavEntries.get(position - HEADER_ITEMS);
    }

    public void setSelectedPosition(int position) {
        int oldSelectedPosition = mSelectedPosition;
        mSelectedPosition = position + HEADER_ITEMS;
        notifyItemChanged(mSelectedPosition);
        notifyItemChanged(oldSelectedPosition);
    }

    public class NavHeaderViewHolder extends RecyclerView.ViewHolder {
        public NavHeaderViewHolder(View itemView) {
            super(itemView);
            ViewCompat.setElevation(itemView, UiUtils.convertDpToPixel(R.dimen.elevation, itemView.getContext()));
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

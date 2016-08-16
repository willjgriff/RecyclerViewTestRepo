package com.github.willjgriff.playground.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.willjgriff.playground.NotificationCreator;
import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.coord.ParallaxActivity;
import com.github.willjgriff.playground.movies.MovieDetailsActivity;
import com.github.willjgriff.playground.utils.UiUtils;

import java.util.List;

/**
 * Created by Will on 21/02/2016.
 *
 * See PeopleAdapter for an alternative way of dealing with multiple item types.
 */
public class NavigationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_ITEMS = 1;
    private List<NavigationEntry> mNavEntries;
    private int mSelectedPosition;

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

    // See {@link com.github.willjgriff.playground.lists.adapters.PeopleAdapter} for a setup
    // with headers that doesn't use reflection.
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
        private Button mNotifyButton;

        public NavHeaderViewHolder(View itemView) {
            super(itemView);
            mNotifyButton = (Button) itemView.findViewById(R.id.view_navigation_header_item_button);
            mNotifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NotificationCreator.createNotification(itemView.getContext(), "El Notify", "Elocation", getMovieScreenIntent());
                }
            });
            ViewCompat.setElevation(itemView, UiUtils.convertDpToPixel(R.dimen.elevation, itemView.getContext()));
        }

        private Intent getMovieScreenIntent() {
            return new Intent(itemView.getContext(), ParallaxActivity.class);
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

package navigation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.will.Playground.R;

import java.util.List;

/**
 * Created by Will on 21/02/2016.
 */
public class NavigationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    enum ItemType {
        ITEM,
        HEADER
    }

    List<NavigationEntry> mNavEntries;

    public NavigationRecyclerViewAdapter(List<NavigationEntry> navEntries) {
        mNavEntries = navEntries;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == ItemType.HEADER.ordinal()) {
            View header = layoutInflater.inflate(R.layout.adapter_navigation_header, parent, false);
            viewHolder = new NavHeaderViewHolder(header);
        } else if (viewType == ItemType.ITEM.ordinal()) {
            View navEntry = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_navigation_item, parent, false);
            viewHolder = new NavRecyclerViewHolder(navEntry);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NavHeaderViewHolder) {

        } else if (holder instanceof NavRecyclerViewHolder) {
            ((NavRecyclerViewHolder) holder).bindView(getItem(position).getNavigationTitle(), getItem(position).getNavigationClickListener());
        }
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = ItemType.ITEM.ordinal();
        if (isPositionHeader(position)) {
            itemType = ItemType.HEADER.ordinal();
        }
        return itemType;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return mNavEntries.size() + 1;
    }

    private NavigationEntry getItem(int position) {
        return mNavEntries.get(position - 1);
    }

    public static class NavHeaderViewHolder extends RecyclerView.ViewHolder {
        public NavHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class NavRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mNavigationTitle;
        private LinearLayout mNavigationLayout;

        public NavRecyclerViewHolder(View itemView) {
            super(itemView);
            mNavigationTitle = (TextView) itemView.findViewById(R.id.navigation_recycler_view_item_text);
            mNavigationLayout = (LinearLayout) itemView.findViewById(R.id.navigation_recycler_view_item_layout);
        }

        public void bindView(String navTitle, View.OnClickListener navClickListener) {
            mNavigationTitle.setText(navTitle);
            mNavigationLayout.setOnClickListener(navClickListener);
        }
    }
}

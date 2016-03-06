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
public class NavigationRecyclerViewAdapter extends RecyclerView.Adapter<NavigationRecyclerViewAdapter.NavRecyclerViewHolder> {

    List<NavigationEntry> mNavEntries;

    public NavigationRecyclerViewAdapter(List<NavigationEntry> navEntries) {
        mNavEntries = navEntries;
    }

    @Override
    public NavRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View navEntry = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_navigation_item, parent, false);
        return new NavRecyclerViewHolder(navEntry);
    }

    @Override
    public void onBindViewHolder(NavRecyclerViewHolder holder, int position) {
        holder.bindView(mNavEntries.get(position).getNavigationTitle(), mNavEntries.get(position).getNavigationClickListener());
    }

    @Override
    public int getItemCount() {
        return mNavEntries.size();
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

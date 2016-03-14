package fab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.will.Playground.R;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import lists.adapters.PeopleRecyclerViewAdapter;
import lists.data.People;

/**
 * Created by Will on 04/02/2016.
 */
public class FabFragment extends Fragment {

    private RecyclerView mFabList;
    private FloatingActionsMenu mFabMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fab, container, false);

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.fragment_fab_coordinator);

        mFabList = (RecyclerView) view.findViewById(R.id.fragment_fab_recycler_view);
        mFabList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mFabList.setLayoutManager(linearLayoutManager);
        mFabList.setAdapter(new PeopleRecyclerViewAdapter(getContext(), People.getPeople(), R.layout.adapter_recycler_view_item));

        mFabMenu = (FloatingActionsMenu)view.findViewById(R.id.fragment_fab_menu);
        view.findViewById(R.id.fragment_fab_recycler_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFabMenu.collapse();
            }
        });
        
        mFabMenu.collapse();

        view.findViewById(R.id.fragment_fab_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, "FAB-ulous", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

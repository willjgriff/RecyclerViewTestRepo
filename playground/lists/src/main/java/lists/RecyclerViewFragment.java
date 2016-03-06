package lists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.will.Playground.R;

import java.util.ArrayList;
import java.util.List;

import lists.adapters.PeopleRecyclerViewAdapter;
import lists.data.Person;

import static lists.ListsFragment.FRAGMENT_ARGS;

/**
 * Created by Will on 01/02/2016.
 */
public class RecyclerViewFragment extends Fragment {

    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view_layout, container, false);

        List<Person> people = new ArrayList<>();
        Bundle args = getArguments();
        if (args != null) {
            people = args.getParcelableArrayList(FRAGMENT_ARGS);
        }

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        PeopleRecyclerViewAdapter peopleRecyclerViewAdapter = new PeopleRecyclerViewAdapter(getContext(), people, R.layout.adapter_recycler_view_item);
        mRecyclerView.setAdapter(peopleRecyclerViewAdapter);

        return view;
    }
}

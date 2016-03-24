package com.playground.will.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.will.Playground.R;
import com.playground.will.lists.adapters.PeopleRecyclerViewAdapter;
import com.playground.will.lists.adapters.PeopleRecyclerViewWithClickAdapter;
import com.playground.will.lists.adapters.PeopleRecyclerViewWithClickAdapter.RecyclerViewListener;
import com.playground.will.lists.data.Person;

import java.util.ArrayList;
import java.util.List;

import static com.playground.will.lists.ListsFragment.FRAGMENT_ARGS;

/**
 * Created by Will on 01/02/2016.
 */
public class RecyclerViewFragment extends Fragment implements RecyclerViewListener {

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

        PeopleRecyclerViewAdapter peopleRecyclerViewAdapter = new PeopleRecyclerViewWithClickAdapter(getContext(), people, this);
        mRecyclerView.setAdapter(peopleRecyclerViewAdapter);

        return view;
    }

    @Override
    public void recyclerViewItemClick(Person person, View transitionImage, View transitionName, View transitionAge) {
        Intent intent = new Intent(getActivity(), RecyclerItemActivity.class);
        intent.putExtra(RecyclerItemActivity.EXTRA_PERSON, person);

        Pair<View, String> pairImage = Pair.create(transitionImage, "item_image");
        Pair<View, String> pairName = Pair.create(transitionName, "item_name");
        Pair<View, String> pairAge = Pair.create(transitionAge, "item_age");

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairImage, pairName, pairAge);
        getActivity().startActivity(intent, options.toBundle());
    }
}

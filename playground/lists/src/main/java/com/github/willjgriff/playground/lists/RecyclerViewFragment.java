package com.github.willjgriff.playground.lists;

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

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.lists.adapters.PeopleAdapter;
import com.github.willjgriff.playground.lists.adapters.viewholders.PersonViewHolder.PersonItemListener;
import com.github.willjgriff.playground.lists.model.PeopleAdapterHeader;
import com.github.willjgriff.playground.lists.model.PeopleAdapterModel;
import com.github.willjgriff.playground.lists.model.PeopleAdapterPerson;
import com.github.willjgriff.playground.lists.model.Person;

import java.util.ArrayList;
import java.util.List;

import static com.github.willjgriff.playground.lists.ListsFragment.FRAGMENT_ARGS;

/**
 * Created by Will on 01/02/2016.
 */
public class RecyclerViewFragment extends Fragment implements PersonItemListener {

    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view_layout, container, false);
        setupPeopleList(view);
        return view;
    }

    private void setupPeopleList(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        PeopleAdapter peopleAdapter = new PeopleAdapter(getPeople(), this);
        mRecyclerView.setAdapter(peopleAdapter);
    }

    private List<PeopleAdapterModel> getPeople() {
        List<PeopleAdapterModel> peopleAdapterList = new ArrayList<>();
        peopleAdapterList.add(new PeopleAdapterHeader());

        List<Person> people = new ArrayList<>();
        Bundle args = getArguments();
        if (args != null) {
            people = args.getParcelableArrayList(FRAGMENT_ARGS);
        }
        peopleAdapterList.addAll(PeopleAdapterPerson.getPeopleAdapterList(people));

        return peopleAdapterList;
    }

    @Override
    public void personItemClick(Person person, View transitionImage, View transitionName, View transitionAge) {
        Intent intent = new Intent(getActivity(), RecyclerItemActivity.class);
        intent.putExtra(RecyclerItemActivity.EXTRA_PERSON, person);

        Pair<View, String> pairImage = Pair.create(transitionImage, "item_image");
        Pair<View, String> pairName = Pair.create(transitionName, "item_name");
        Pair<View, String> pairAge = Pair.create(transitionAge, "item_age");

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairImage, pairName, pairAge);
        getActivity().startActivity(intent, options.toBundle());
    }
}

package com.github.willjgriff.playground.coord;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.lists.adapters.PeopleAdapter;
import com.github.willjgriff.playground.lists.adapters.viewholders.PersonViewHolder.PersonItemListener;
import com.github.willjgriff.playground.lists.model.PeopleAdapterModel;
import com.github.willjgriff.playground.lists.model.PeopleAdapterPerson;
import com.github.willjgriff.playground.lists.model.Person;
import com.github.willjgriff.playground.lists.model.data.People;

import java.util.List;

/**
 * Created by Will on 22/06/2016.
 */

public class ParallaxFragment extends Fragment implements PersonItemListener {

    private static final int ANIMATION_DURATION = 330;
    private static final int ANIMATION_DELAY = 330;

    ParallaxFragmentListener mParallaxFragmentListener;
    View mAnimatedView;
    FloatingActionButton mFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coord_parallax_animation, container, false);
        setupPeopleList(view);
        setupToolbar(view);
        mAnimatedView = view.findViewById(R.id.fragment_coord_parallax_toolbar_animated_view);
        view.findViewById(R.id.fragment_coord_toolbar_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateView(0);
            }
        });


//        mAnimatedView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mAnimatedView.setY(mAnimatedView.getY() + mAnimatedView.getHeight());
//                animateView();
//        
//                mAnimatedView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });

        mAnimatedView.post(new Runnable() {
            @Override
            public void run() {
                mAnimatedView.setY(mAnimatedView.getY() + mAnimatedView.getHeight());

                animateView(ANIMATION_DELAY);
            }
        });


        return view;
    }

    private void animateView(int delay) {
        mAnimatedView
                .animate()
                .y(mAnimatedView.getY() - mAnimatedView.getHeight())
                .setDuration(ANIMATION_DURATION)
                .setStartDelay(delay)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        mAnimatedView.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mAnimatedView.setY(mAnimatedView.getY() - 200);

    }

    private void setupToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.fragment_coord_toolbar_toolbar);
        // I would never cast getActivity to AppCompatActivity in reality. I would use a listener.
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupPeopleList(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_coord_toolbar_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<PeopleAdapterModel> peopleAdapterList = PeopleAdapterPerson.getPeopleAdapterList(People.getPeople());
        PeopleAdapter adapter = new PeopleAdapter(peopleAdapterList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mParallaxFragmentListener = (ParallaxFragmentListener) context;
    }

    @Override
    public void personItemClick(Person person, View transitionImage, View transitionName, View transitionAge) {
        mParallaxFragmentListener.switchFragment();
    }

    public interface ParallaxFragmentListener {
        void switchFragment();
    }
}

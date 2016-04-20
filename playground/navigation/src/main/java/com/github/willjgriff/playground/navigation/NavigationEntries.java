package com.github.willjgriff.playground.navigation;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.coord.CoordsFragment;
import com.github.willjgriff.playground.ethereum.views.BlockFragment;
import com.github.willjgriff.playground.lists.ListsFragment;
import com.github.willjgriff.playground.movies.Views.TopMoviesFragment;
import com.github.willjgriff.playground.soquestions.StackOverflowQuestionsFragment;
import com.github.willjgriff.playground.tabs.TabLayoutFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.github.willjgriff.playground.navigation.NavigationEntries.EntryTag.COORD;
import static com.github.willjgriff.playground.navigation.NavigationEntries.EntryTag.ETHEREUM;
import static com.github.willjgriff.playground.navigation.NavigationEntries.EntryTag.LISTS;
import static com.github.willjgriff.playground.navigation.NavigationEntries.EntryTag.SO_QUESTIONS;
import static com.github.willjgriff.playground.navigation.NavigationEntries.EntryTag.TABS;
import static com.github.willjgriff.playground.navigation.NavigationEntries.EntryTag.TOP_MOVIES;

/**
 * Created by Will on 03/04/2016.
 */
public class NavigationEntries {

    private Context mContext;
    private NavigationListener mNavigationListener;

    interface NavigationListener {
        void onEntryClicked(Fragment fragment, EntryTag entryTag);
    }

    enum EntryTag {
        LISTS(R.string.navigation_list_activity, R.integer.navigation_list_activity),
        COORD(R.string.navigation_coord_fragment, R.integer.navigation_coord_fragment),
        TABS(R.string.navigation_tab_layout_fragment, R.integer.navigation_tab_layout_fragment),
        SO_QUESTIONS(R.string.navigation_so_questions_fragment, R.integer.navigation_so_questions_fragment),
        TOP_MOVIES(R.string.navigation_top_movies_fragment, R.integer.navigation_top_movies_fragment),
        ETHEREUM(R.string.navigation_ethereum, R.integer.navigation_ethereum);

        private int mTitle;
        private int mPosition;

        EntryTag(@StringRes int title, int position) {
            mTitle = title;
            mPosition = position;
        }

        public int getTitle() {
            return mTitle;
        }

        public int getPosition() {
            return mPosition;
        }
    }

    public NavigationEntries(Context context, NavigationListener navigationListener) {
        this.mContext = context;
        this.mNavigationListener = navigationListener;
    }

    public List<NavigationEntry> getNavEntries() {
        List<NavigationEntry> navEntries = new ArrayList<>();

        navEntries.add(new NavigationEntry(mContext.getString(LISTS.getTitle()), mContext.getResources().getInteger(LISTS.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigationListener.onEntryClicked(new ListsFragment(), LISTS);
            }
        }));
        navEntries.add(new NavigationEntry(mContext.getString(COORD.getTitle()), mContext.getResources().getInteger(COORD.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigationListener.onEntryClicked(new CoordsFragment(), COORD);
            }
        }));
        navEntries.add(new NavigationEntry(mContext.getString(TABS.getTitle()), mContext.getResources().getInteger(TABS.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigationListener.onEntryClicked(new TabLayoutFragment(), TABS);
            }
        }));
        navEntries.add(new NavigationEntry(mContext.getString(SO_QUESTIONS.getTitle()), mContext.getResources().getInteger(SO_QUESTIONS.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigationListener.onEntryClicked(new StackOverflowQuestionsFragment(), SO_QUESTIONS);
            }
        }));
        navEntries.add(new NavigationEntry(mContext.getString(TOP_MOVIES.getTitle()), mContext.getResources().getInteger(TOP_MOVIES.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigationListener.onEntryClicked(new TopMoviesFragment(), TOP_MOVIES);
            }
        }));
        navEntries.add(new NavigationEntry(mContext.getString(ETHEREUM.getTitle()), mContext.getResources().getInteger(ETHEREUM.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigationListener.onEntryClicked(new BlockFragment(), ETHEREUM);
            }
        }));

        Collections.sort(navEntries, new Comparator<NavigationEntry>() {
            @Override
            public int compare(NavigationEntry lhs, NavigationEntry rhs) {
                return lhs.getNavigationPosition().compareTo(rhs.getNavigationPosition());
            }
        });

        return navEntries;
    }
}

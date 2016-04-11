package com.github.willjgriff.playground.movies.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.movies.Adapters.TopMoviesMvpAdapter;
import com.github.willjgriff.playground.movies.Presenters.TopMoviesPresenter;
import com.github.willjgriff.playground.movies.Presenters.TopMoviesPresenterImpl;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpFragment;

import java.util.List;

/**
 * Created by Will on 29/03/2016.
 */
public class TopMoviesFragment extends MvpFragment<TopMoviesPresenter> implements TopMoviesView {

    private TopMoviesMvpAdapter mAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView mTopMovieList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_movies, container, false);

        mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_top_movies_progress_bar);

        mTopMovieList = (RecyclerView) view.findViewById(R.id.fragment_top_movies_recycler_view);
        mTopMovieList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mTopMovieList.setLayoutManager(linearLayoutManager);

        mAdapter = new TopMoviesMvpAdapter();
        mTopMovieList.setAdapter(mAdapter);

        // TODO: Add a clear method to the adapter so we can see if this actually updates the list.
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_top_movies_swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().fetchTopMovies();
            }
        });


        return view;
    }

    @Override
    protected TopMoviesPresenter setPresenter() {
        return new TopMoviesPresenterImpl();
    }

    @Override
    public void showEmpty() {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void showData(List<Movie> movie) {
        mAdapter.clearAndAddAll(movie);
        mProgressBar.setVisibility(View.INVISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
    }


}

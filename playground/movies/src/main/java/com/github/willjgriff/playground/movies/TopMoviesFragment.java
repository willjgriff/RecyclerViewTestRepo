package com.github.willjgriff.playground.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.movies.Views.TopMoviesView;
import com.github.willjgriff.playground.network.model.movies.MovieListItem;
import com.github.willjgriff.playground.movies.Adapters.TopMoviesMvpAdapter;
import com.github.willjgriff.playground.movies.Presenters.TopMoviesPresenter;
import com.github.willjgriff.playground.movies.Presenters.TopMoviesPresenterImpl;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.Adapter.MvpRecyclerListAdapter;
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
        mAdapter.setOnClickListener(new MvpRecyclerListAdapter.OnItemClickListener<MovieListItem>() {
            @Override
            public void onItemClicked(View view, MovieListItem movieListItem) {
                getPresenter().onMovieItemClicked(movieListItem.getId());
            }
        });
        mTopMovieList.setAdapter(mAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_top_movies_swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().loadDataModel();
            }
        });

        return view;
    }

    @Override
    protected TopMoviesPresenter createPresenter() {
        return new TopMoviesPresenterImpl();
    }

    @Override
    public void showEmpty() {
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTopMovieList.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showData(List<MovieListItem> movieListItem) {
        mAdapter.clearAndAddAll(movieListItem);
        mProgressBar.setVisibility(View.INVISIBLE);
        mTopMovieList.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void openMovieDetailScreen(String movieId) {
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movieId);
        startActivity(intent);
    }
}

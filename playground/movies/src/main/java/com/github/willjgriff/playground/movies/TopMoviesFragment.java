package com.github.willjgriff.playground.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.RetrofitCalls;
import com.github.willjgriff.playground.api.model.movies.TopMovies;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 29/03/2016.
 */
public class TopMoviesFragment extends Fragment {

    private TopMoviesAdapter mAdapter;
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_movies, container, false);

        mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_top_movies_progress_bar);

        RecyclerView topMovieList = (RecyclerView) view.findViewById(R.id.fragment_top_movies_recycler_view);
        topMovieList.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        topMovieList.setLayoutManager(linearLayoutManager);

        mAdapter = new TopMoviesAdapter(getContext());
        topMovieList.setAdapter(mAdapter);

        loadTopMovies();

        return view;
    }

    private void loadTopMovies() {
        RetrofitCalls.topMoviesCall().enqueue(new Callback<TopMovies>() {
            @Override
            public void onResponse(Response<TopMovies> response, Retrofit retrofit) {
                mAdapter.setMovies(response.body().getTopMovies());
                dismissProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Tag", "Failed to connect to The Move Db");
            }
        });
    }

    private void dismissProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}

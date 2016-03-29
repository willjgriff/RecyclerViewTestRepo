package com.github.willjgriff.playground.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.RetrofitCalls;
import com.github.willjgriff.playground.api.model.movies.TopMovies;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 29/03/2016.
 */
public class TopMoviesFragment extends Fragment {

    ArrayAdapter<Object> mAdapter;
    ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_movies, container, false);

        mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_top_movies_progress_bar);
        mAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<>());

        ListView topMoviesList = (ListView) view.findViewById(R.id.fragment_top_movies_list);
        topMoviesList.setAdapter(mAdapter);

        showProgressBar();
        loadTopMovies();

        return view;
    }

    private void loadTopMovies() {
        RetrofitCalls.topMoviesCall().enqueue(new Callback<TopMovies>() {
            @Override
            public void onResponse(Response<TopMovies> response, Retrofit retrofit) {
                mAdapter.addAll(response.body().getTopMovies());
                dismissProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Tag", "Failed to connect to The Move Db");
            }
        });
    }

    private void showProgressBar() {
        mAdapter.clear();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void dismissProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}

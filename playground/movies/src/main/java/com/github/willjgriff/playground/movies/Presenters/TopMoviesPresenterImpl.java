package com.github.willjgriff.playground.movies.Presenters;

import android.util.Log;

import com.github.willjgriff.playground.movies.data.TopMoviesData;
import com.github.willjgriff.playground.movies.data.TopMoviesDataListener;
import com.github.willjgriff.playground.network.model.movies.MovieListItem;
import com.github.willjgriff.playground.network.model.movies.TopMovies;
import com.github.willjgriff.playground.movies.Views.TopMoviesView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BaseListLoadingPresenter;

/**
 * Created by Will on 09/04/2016.
 */
public class TopMoviesPresenterImpl extends BaseListLoadingPresenter<MovieListItem, TopMoviesView> implements TopMoviesPresenter {

    TopMoviesData mTopMoviesData;

    public TopMoviesPresenterImpl(TopMoviesData topMoviesData) {
        mTopMoviesData = topMoviesData;
    }

    // TODO: We should cancel the call when we detach from the view.
    @Override
    public void loadDataModel() {
        setLoading(true);

        mTopMoviesData.getTopMovies(new TopMoviesDataListener() {
            @Override
            public void success(TopMovies topMovies) {
                setModel(topMovies.getTopMovies());
                setLoading(false);
            }

            @Override
            public void failed(Throwable throwable) {
                setLoading(false);
                Log.e("Tag", "Failed to connect to The Movie Db");
            }
        });
    }

    @Override
    public void unbindView() {
        super.unbindView();
        mTopMoviesData.cancel();
    }

    @Override
    public void onMovieItemClicked(String movieId) {
        view().openMovieDetailScreen(movieId);
    }
}

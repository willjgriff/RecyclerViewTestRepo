package com.github.willjgriff.playground.movies.data;

import android.support.annotation.NonNull;

import com.github.willjgriff.playground.PlaygroundApplication;
import com.github.willjgriff.playground.network.model.movies.TopMovies;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

import static com.github.willjgriff.playground.network.model.movies.MovieListItem.RELEASE_DATE_FORMAT;

/**
 * Created by Will on 18/07/2016.
 *
 * This allows us to do automation testing without hitting endpoints.
 * We still get real images from the url's the fake endpoint returns though.
 * Need to work out how to deal with that.
 */

public class TopMoviesData {

	public void getTopMovies(TopMoviesDataListener topMoviesDataListener) {
		Gson gson = new GsonBuilder().setDateFormat(RELEASE_DATE_FORMAT).create();
		String movieListJson = getMovieListJson();
		TopMovies mockTopMovies = gson.fromJson(movieListJson, TopMovies.class);

		topMoviesDataListener.success(mockTopMovies);
	}

	@NonNull
	private String getMovieListJson() {
		String json = "";
		try {
			InputStream movieListInputStream = PlaygroundApplication.app().getApplicationContext().getAssets().open("TheMovieDbListJson.json");
			int size = movieListInputStream.available();
			byte[] buffer = new byte[size];

			movieListInputStream.read(buffer);
			movieListInputStream.close();

			json = new String(buffer, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public void cancel() {
	}
}

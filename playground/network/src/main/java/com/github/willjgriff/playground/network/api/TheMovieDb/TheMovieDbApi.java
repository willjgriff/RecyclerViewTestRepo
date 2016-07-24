package com.github.willjgriff.playground.network.api.TheMovieDb;

import com.github.willjgriff.playground.network.endpoints.ApiTheMovieDb;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.github.willjgriff.playground.network.api.ApiUris.TheMovieDb.MOVIES_API_KEY;
import static com.github.willjgriff.playground.network.api.ApiUris.TheMovieDb.URI_THE_MOVIE_DB;
import static com.github.willjgriff.playground.network.model.movies.MovieListItem.RELEASE_DATE_FORMAT;

/**
 * Created by Will on 18/04/2016.
 */
public class TheMovieDbApi {

	public static ApiTheMovieDb getTheMovieDbApi() {
		// The gson enables automatic conversion from the specified date format to a date object.
		Gson gson = new GsonBuilder().setDateFormat(RELEASE_DATE_FORMAT).create();
		OkHttpClient client = getMoviesClientWithApiKey(MOVIES_API_KEY);

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(URI_THE_MOVIE_DB)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.client(client)
//			.client(new OkHttpClient())
			.build();

		return retrofit.create(ApiTheMovieDb.class);
	}

	private static OkHttpClient getMoviesClientWithApiKey(final String apiKey) {
		Interceptor requestInterceptor = new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request originalRequest = chain.request();
				HttpUrl.Builder builder = originalRequest.url().newBuilder()
					.addQueryParameter("api_key", apiKey);
				Request newRequest = originalRequest.newBuilder()
					.url(builder.build())
					.method(originalRequest.method(), originalRequest.body())
					.build();

				return chain.proceed(newRequest);
			}
		};
		return new OkHttpClient().newBuilder().addInterceptor(requestInterceptor).build();
	}

}

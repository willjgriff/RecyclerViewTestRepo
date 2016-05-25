package com.github.willjgriff.playground.network.api;

import com.github.willjgriff.playground.BuildConfig;

/**
 * Created by Will on 31/03/2016.
 */
public class ApiUris {

    public static final class StackOverflow {

        public static final String URI_STACK_OVERFLOW = "https://api.stackexchange.com/";
    }

    public static final class TheMovieDb {

        public static final String MOVIES_API_KEY = BuildConfig.THE_MOVIE_DB_API_KEY;

        public static final String URI_THE_MOVIE_DB = "https://api.themoviedb.org/";

        public static final String URI_MOVIE_IMAGE = "http://image.tmdb.org/t/p/";
    }

    public static final class Etherchain {

        public static final String URI_ETHERCHAIN = "https://etherchain.org/";
    }
}

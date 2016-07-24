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

        // Not private so we can mock it. Should probably find a different method for doing that.
        public static String URI_THE_MOVIE_DB = "https://api.themoviedb.org/";

        // TODO: This should be set by the config file requested at app start.
        public static final String URI_MOVIE_IMAGE = "http://image.tmdb.org/t/p/";
    }

    public static final class Etherchain {

        public static final String URI_ETHERCHAIN = "https://etherchain.org/";
    }
}

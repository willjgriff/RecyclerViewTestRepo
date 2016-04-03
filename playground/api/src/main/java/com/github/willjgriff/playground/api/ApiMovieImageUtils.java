package com.github.willjgriff.playground.api;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.model.movies.MoviesConfig;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.github.willjgriff.playground.api.ApiConstants.TheMovieDb.URI_MOVIE_IMAGE;

/**
 * Created by Will on 01/04/2016.
 */
public class ApiMovieImageUtils {

    public static void loadMovieImageInto(final String imageUri, final ImageView imageView) {

        RetrofitCalls.moviesConfigCall().enqueue(new Callback<MoviesConfig>() {
            @Override
            public void onResponse(Response<MoviesConfig> response, Retrofit retrofit) {
                List<String> posterSizes = response.body().getImageConfig().getPosterSizes();

                // Should do more processing here to determine which size to use (eg sort list by size and use largest)
                String largePosterSize = posterSizes.get(0);
                showImage(imageUri, imageView)
                        .withPlaceholder(R.drawable.movie_poster_placeholder)
                        .withImageSize(largePosterSize)
                        .now();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public static Builder showImage(String imageUri, ImageView imageView) {
        return new Builder(imageUri, imageView);
    }

    private static class Builder {

        private String mImageUri;
        private ImageView mImageView;
        private int mPlaceholder;
        private int mErrorImage;
        private String mImageSize;

        public Builder(String mImageUri, ImageView mImageView) {
            this.mImageUri = mImageUri;
            this.mImageView = mImageView;
        }

        public Builder withPlaceholder(@DrawableRes int placeholder) {
            mPlaceholder = placeholder;
            return this;
        }

        public Builder withErrorImage(@DrawableRes int errorImage) {
            mErrorImage = errorImage;
            return this;
        }

        public Builder withImageSize(String imageSize) {
            mImageSize = imageSize;
            return this;
        }

        void now() {
            RequestCreator picassoLoader = Picasso.with(mImageView.getContext()).load(getCompleteImageUri()).fit();

            if (mPlaceholder != 0) {
                picassoLoader.placeholder(mPlaceholder);
            }

            if (mErrorImage != 0) {
                picassoLoader.error(mErrorImage);
            }

            picassoLoader.into(mImageView);
        }

        @NonNull
        private String getCompleteImageUri() {
            String completeUri = URI_MOVIE_IMAGE;

            if (mImageSize != null) {
                completeUri = completeUri.concat(mImageSize);
            } else {
                completeUri = completeUri.concat("original");
            }

            completeUri = completeUri + mImageUri;

            return completeUri;
        }
    }
}

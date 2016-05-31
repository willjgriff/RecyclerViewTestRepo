package com.github.willjgriff.playground.network.utils;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.github.willjgriff.playground.network.utils.MovieImageSize.ImageSize;
import com.github.willjgriff.playground.network.utils.MovieImageSize.ImageType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import static com.github.willjgriff.playground.network.api.ApiUris.TheMovieDb.URI_MOVIE_IMAGE;

/**
 * Created by Will on 01/04/2016.
 */
public class MovieApiImageUtils {

    public static Builder showImage(String imageUri, ImageView imageView) {
        return new Builder(imageUri, imageView);
    }

    public static class Builder {

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

        public Builder withTypeSize(ImageType type, ImageSize size) {
            MovieImageSize movieImageSize = new MovieImageSize(type, size);
            mImageSize = movieImageSize.getSizeString(mImageView.getContext());
            return this;
        }

        public void now() {
            RequestCreator picassoLoader = Picasso.with(mImageView.getContext()).load(getCompleteImageUri());

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

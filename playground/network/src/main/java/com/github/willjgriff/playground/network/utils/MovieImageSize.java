package com.github.willjgriff.playground.network.utils;

import android.content.Context;

import com.github.willjgriff.playground.network.model.movies.MoviesConfig;
import com.github.willjgriff.playground.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 09/04/2016.
 * <p>
 * Would ideally make Unit tests for this class.
 *
 * Example Json representing sizes:
 * "backdrop_sizes": ["w300","w780","w1280","original"],
 * "logo_sizes": ["w45","w92","w154","w185","w300","w500","original"]
 */
public class MovieImageSize {

    ImageType mImageType;
    ImageSize mImageSize;

    public enum ImageType {
        BACKDROP,
        LOGO,
        POSTER,
        PROFILE,
        STILL;
    }

    public enum ImageSize {
        SMALL,
        MEDIUM,
        LARGE;
    }

    public MovieImageSize(ImageType type, ImageSize size) {
        mImageType = type;
        mImageSize = size;
    }

    public String getSizeString(Context context) {
        List<String> imageSizes = new ArrayList<>();
        String imageSize = null;
        MoviesConfig moviesConfig = SharedPreferenceUtils.readObjectFromPreferences(context, SharedPreferenceUtils.SHARED_MOVIES_CONFIG, MoviesConfig.class);

        switch (mImageType) {
            case BACKDROP:
                imageSizes = moviesConfig.getImageConfig().getBackdropSizes();
                break;
            case LOGO:
                imageSizes = moviesConfig.getImageConfig().getLogoSizes();
                break;
            case POSTER:
                imageSizes = moviesConfig.getImageConfig().getPosterSizes();
                break;
            case PROFILE:
                imageSizes = moviesConfig.getImageConfig().getPosterSizes();
                break;
            case STILL:
                imageSizes = moviesConfig.getImageConfig().getStillSizes();
        }

        switch (mImageSize) {
            case SMALL:
                imageSize = imageSizes.get(0);
                break;
            case MEDIUM:
                imageSize = imageSizes.get((imageSizes.size() / 2) - 1);
                break;
            case LARGE:
                // Note that the last item in the list is always 'original' not a size number. So use the one before it.
                imageSize = imageSizes.get(imageSizes.size() - 2);
        }

        return imageSize != null ? imageSize : "original";
    }

}

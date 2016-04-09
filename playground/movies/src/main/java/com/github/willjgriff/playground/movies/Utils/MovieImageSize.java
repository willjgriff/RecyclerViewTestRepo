package com.github.willjgriff.playground.movies.Utils;

import android.content.Context;

import com.github.willjgriff.playground.api.model.movies.MoviesConfig;
import com.github.willjgriff.playground.utils.SharedPreferenceUtils;

import java.util.List;

/**
 * Created by Will on 09/04/2016.
 * </p>
 * Would ideally make Unit tests for this class.
 *
 * Example Json representing sizes:
 * "backdrop_sizes": ["w300","w780","w1280","original"],
 * "logo_sizes": ["w45","w92","w154","w185","w300","w500","original"]
 */
public class MovieImageSize {

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

    public static String imageSizeString(Context context, ImageType type, ImageSize size) {
        MoviesConfig moviesConfig = SharedPreferenceUtils.readObjectFromPreferences(context, SharedPreferenceUtils.SHARED_MOVIES_CONFIG, MoviesConfig.class);
        switch (type) {
            case BACKDROP:
                return getSize(size, moviesConfig.getImageConfig().getBackdropSizes());
            case LOGO:
                return getSize(size, moviesConfig.getImageConfig().getLogoSizes());
            case POSTER:
                return getSize(size, moviesConfig.getImageConfig().getPosterSizes());
            case PROFILE:
                return getSize(size, moviesConfig.getImageConfig().getPosterSizes());
            case STILL:
                return getSize(size, moviesConfig.getImageConfig().getStillSizes());
        }
        return "original";
    }

    private static String getSize(ImageSize size, List<String> imageSizes) {
        switch (size) {
            case SMALL:
                return imageSizes.get(0);
            case MEDIUM:
                return imageSizes.get((imageSizes.size() / 2) - 1);
            case LARGE:
                // Note that the last item in the list is always 'original' not a size. So use the one before it.
                return imageSizes.get(imageSizes.size() - 2);
        }
        return "original";
    }

}

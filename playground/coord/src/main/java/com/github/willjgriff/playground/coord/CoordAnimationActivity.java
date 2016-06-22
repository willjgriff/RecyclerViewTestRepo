package com.github.willjgriff.playground.coord;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.coord.ParallaxFragment.ParallaxFragmentListener;
import com.github.willjgriff.playground.movies.TopMoviesFragment;

/**
 * Created by Will on 22/06/2016.
 */

public class CoordAnimationActivity extends AppCompatActivity implements ParallaxFragmentListener {

    public static void startCoordAnimationActivity(Context context) {
        Intent intent = new Intent(context, CoordAnimationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_coord);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_animation_coord_container, new ParallaxFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void switchFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_animation_coord_container, new TopMoviesFragment())
                .addToBackStack(null)
                .commit();
    }
}

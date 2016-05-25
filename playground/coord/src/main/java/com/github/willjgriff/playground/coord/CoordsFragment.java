package com.github.willjgriff.playground.coord;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.willjgriff.playground.R;

/**
 * Created by Will on 16/03/2016.
 */
public class CoordsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coord_buttons, container, false);
        view.findViewById(R.id.fragment_coord_buttons_fab_coord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fabCoordIntent = new Intent(getActivity(), CoordToolbarActivity.class);
                getActivity().startActivity(fabCoordIntent);
            }
        });
        view.findViewById(R.id.fragment_coord_buttons_toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toolbarCoordIntent = new Intent(getActivity(), CoordParallaxActivity.class);
                getActivity().startActivity(toolbarCoordIntent);
            }
        });
        return view;
    }
}

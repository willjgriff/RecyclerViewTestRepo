package com.example.will.recyclerviewtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Will on 01/02/2016.
 */
public class OtherFragment extends Fragment {

    public static final String FRAGMENT_OTHER_TAG = "com.example.will.recyclerviewtest.RecyclerViewTest:FRAGMENT_OTHER_TAG";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);

        // DO SOMETHING NEW

        return view;
    }
}

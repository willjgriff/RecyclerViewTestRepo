package com.example.will.Playground;

import android.os.Bundle;

/**
 * Created by Will on 04/02/2016.
 */
public class OtherActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        // Can we fix this warning...?
        getSupportActionBar().setTitle("El Titlei");
        getSupportActionBar().setSubtitle("Subdubbydub");

    }
}

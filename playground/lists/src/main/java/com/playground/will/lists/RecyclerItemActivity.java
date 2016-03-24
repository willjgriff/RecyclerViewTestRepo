package com.playground.will.lists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.will.Playground.R;
import com.playground.will.lists.data.Person;

/**
 * Created by Will on 22/03/2016.
 */
public class RecyclerItemActivity extends AppCompatActivity {

    public static final String EXTRA_PERSON = "com.playground.will.lists.RecyclerItemActivity;EXTRA_PERSON";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_item_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_recycler_item_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        Person person = extras.getParcelable(EXTRA_PERSON);
        ((ImageView) findViewById(R.id.activity_recycler_item_detail_image)).setImageDrawable(ContextCompat.getDrawable(this, person.mPhotoId));
        ((TextView) findViewById(R.id.activity_recycler_item_detail_name)).setText(person.mName);
        ((TextView) findViewById(R.id.activity_recycler_item_detail_age)).setText(person.mAge);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

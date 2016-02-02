package com.example.will.recyclerviewtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.will.recyclerviewtest.ListViewTests.ListViewFragment;
import com.example.will.recyclerviewtest.ListViewTests.RecyclerViewFragment;

import static com.example.will.recyclerviewtest.ListViewTests.ListViewFragment.FRAGMENT_OTHER_TAG;
import static com.example.will.recyclerviewtest.ListViewTests.RecyclerViewFragment.FRAGMENT_RECYCLER_VIEW_TAG;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If we are not restoring the activity (could otherwise override the fragments)
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_recycler_view_fragment, recyclerViewFragment, FRAGMENT_RECYCLER_VIEW_TAG)
                    .commit();
        }

        findViewById(R.id.activity_main_change_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment();
            }
        });
    }

    private void swapFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;

        if (fragmentManager.findFragmentById(R.id.fragment_recycler_view_fragment) instanceof RecyclerViewFragment) {
            fragment = new ListViewFragment();
        } else {
            fragment = new RecyclerViewFragment();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_recycler_view_fragment, fragment, FRAGMENT_OTHER_TAG)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

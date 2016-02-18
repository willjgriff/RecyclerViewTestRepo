package com.example.will.Playground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Will on 04/02/2016.
 */
public class ToolbarActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main_activity:
                startActivity(new Intent(this, ListsActivity.class));
                break;
            case R.id.action_other_activity:
                startActivity(new Intent(this, OtherActivity.class));
                break;
            case R.id.action_navigation_activity:
                startActivity(new Intent(this, NavigationActivity.class));
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}

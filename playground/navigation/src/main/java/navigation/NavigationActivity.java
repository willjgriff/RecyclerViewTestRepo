package navigation;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.will.Playground.ListsActivity;
import com.example.will.Playground.OtherActivity;
import com.example.will.Playground.R;

/**
 * Created by Will on 18/02/2016.
 */
public class NavigationActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mNavigationToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        RecyclerView mNavigationList = (RecyclerView) findViewById(R.id.activity_navigation_list);
        DrawerLayout mNavigationDrawer = (DrawerLayout) findViewById(R.id.activity_navigation_drawer);

        NavigationDrawerSetup navDrawerSetup = new NavigationDrawerSetup(mNavigationDrawer, mNavigationList, getSupportActionBar(), this);
        mNavigationToggle = navDrawerSetup.setupNavigationDrawer();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mNavigationToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mNavigationToggle.onConfigurationChanged(newConfig);
    }

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
            default:
                break;
        }
        return super.onOptionsItemSelected(item) || mNavigationToggle.onOptionsItemSelected(item);
    }
}

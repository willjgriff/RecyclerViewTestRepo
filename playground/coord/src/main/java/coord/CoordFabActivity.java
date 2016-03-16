package coord;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.will.Playground.R;

import lists.adapters.PeopleRecyclerViewAdapter;
import lists.data.People;

/**
 * Created by Will on 04/02/2016.
 */
public class CoordFabActivity extends AppCompatActivity {

    private RecyclerView mCoordList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fab_coord);

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.fragment_fab_coordinator);

        mCoordList = (RecyclerView) findViewById(R.id.fragment_fab_recycler_view);
        mCoordList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mCoordList.setLayoutManager(linearLayoutManager);
        mCoordList.setAdapter(new PeopleRecyclerViewAdapter(this, People.getPeople(), R.layout.adapter_recycler_view_item));

        findViewById(R.id.fragment_fab_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, "FAB-ulous", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}

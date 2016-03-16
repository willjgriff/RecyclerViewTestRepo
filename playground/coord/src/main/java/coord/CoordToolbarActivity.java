package coord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.will.Playground.R;

import lists.adapters.PeopleRecyclerViewAdapter;
import lists.data.People;

/**
 * Created by Will on 13/03/2016.
 */
public class CoordToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_coord_toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fragment_coord_toolbar_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        PeopleRecyclerViewAdapter adapter = new PeopleRecyclerViewAdapter(this, People.getPeople(), R.layout.adapter_recycler_view_item);
        recyclerView.setAdapter(adapter);

//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.fragment_coord_toolbar_toolbar);
//        toolbar.setTitle("COOOOOORDS");
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }
}

package lists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.will.Playground.R;

import java.util.ArrayList;
import java.util.List;

import lists.RecyclerViewDialogFragment.DialogFragmentListener;
import lists.data.People;
import lists.data.Person;

public class ListsFragment extends Fragment implements DialogFragmentListener {

    public static final String FRAGMENT_ARGS = "com.example.will.playground;FRAGMENT_ARGS";
    public static final String DIALOG_TAG = "com.example.will.playground;DIALOG_TAG";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        view.findViewById(R.id.activity_main_change_fragment_recycler_view_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new RecyclerViewFragment(), People.getPeople());
            }
        });

        view.findViewById(R.id.activity_main_change_fragment_list_view_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new ListViewFragment(), People.getPeople());
            }
        });

        view.findViewById(R.id.activity_main_change_fragment_list_fragment_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new ListFragmentFragment(), People.getPeople());
            }
        });

        view.findViewById(R.id.activity_main_change_fragment_dialog_grid_view_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        return view;
    }

    private void openFragment(Fragment fragment, List<Person> data) {
        removeFragmentChildren();

        if (data != null) {
            Bundle fragmentBundle = new Bundle();
            ArrayList<Person> parcelables = new ArrayList<>(data);
            fragmentBundle.putParcelableArrayList(FRAGMENT_ARGS, parcelables);
            fragment.setArguments(fragmentBundle);
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main_fragment, fragment)
                .commit();
    }

    private void openDialog() {
        RecyclerViewDialogFragment recyclerViewDialogFragment = new RecyclerViewDialogFragment();
        recyclerViewDialogFragment.show(getActivity().getSupportFragmentManager(), DIALOG_TAG);
    }

    @Override
    public void itemSelected(Person person) {
        removeFragmentChildren();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment currentFragmentInView = fragmentManager.findFragmentById(R.id.activity_main_fragment);
        if (currentFragmentInView != null) {
            fragmentManager.beginTransaction()
                    .remove(currentFragmentInView)
                    .commit();
        }
        FrameLayout parentFrameLayout = (FrameLayout) getActivity().findViewById(R.id.activity_main_fragment);
        View singleItem = getActivity().getLayoutInflater().inflate(R.layout.adapter_recycler_view_grid_item, parentFrameLayout);
        ((ImageView) singleItem.findViewById(R.id.person_photo)).setImageResource(person.mPhotoId);
        ((TextView) singleItem.findViewById(R.id.person_age)).setText(person.mAge);
        ((TextView) singleItem.findViewById(R.id.person_name)).setText(person.mName);
    }

    private void removeFragmentChildren() {
        FrameLayout parentFrameLayout = (FrameLayout) getActivity().findViewById(R.id.activity_main_fragment);
        if (parentFrameLayout.getChildCount() > 0) {
            ((FrameLayout) getActivity().findViewById(R.id.activity_main_fragment)).removeAllViews();
        }
    }
}

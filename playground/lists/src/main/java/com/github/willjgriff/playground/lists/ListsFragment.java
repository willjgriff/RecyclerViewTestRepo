package com.github.willjgriff.playground.lists;

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
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import com.github.willjgriff.playground.lists.data.People;
import com.github.willjgriff.playground.lists.data.Person;

public class ListsFragment extends Fragment implements RecyclerViewDialogFragment.DialogFragmentListener {

    public static final String FRAGMENT_ARGS = "com.example.will.playground;FRAGMENT_ARGS";
    public static final String DIALOG_TAG = "com.example.will.playground;DIALOG_TAG";

    private FrameLayout mParentFrameLayout;
    private FloatingActionsMenu mFabMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        view.findViewById(R.id.fragment_lists_fab_recycler_view).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new RecyclerViewFragment(), People.getPeople());
            }
        });

        view.findViewById(R.id.fragment_lists_fab_list_view).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new ListViewFragment(), People.getPeople());
            }
        });

        view.findViewById(R.id.fragment_lists_fab_list_fragment).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new ListFragmentFragment(), People.getPeople());
            }
        });

        view.findViewById(R.id.fragment_lists_fab_recycler_view_dialog).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        mParentFrameLayout = (FrameLayout) view.findViewById(R.id.fragment_lists_container);
        mFabMenu = (FloatingActionsMenu) view.findViewById(R.id.fragment_lists_fab_menu);

        openFragment(new RecyclerViewFragment(), People.getPeople());

        return view;
    }

    private void openFragment(Fragment fragment, List<Person> data) {
        closeFabMenu();
        removeFragmentChildren();

        if (data != null) {
            Bundle fragmentBundle = new Bundle();
            ArrayList<Person> parcelables = new ArrayList<>(data);
            fragmentBundle.putParcelableArrayList(FRAGMENT_ARGS, parcelables);
            fragment.setArguments(fragmentBundle);
        }

        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction()
//                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
                .replace(R.id.fragment_lists_container, fragment)
                .commit();
    }

    private void openDialog() {
        closeFabMenu();
        RecyclerViewDialogFragment recyclerViewDialogFragment = new RecyclerViewDialogFragment();
        recyclerViewDialogFragment.show(getChildFragmentManager(), DIALOG_TAG);
    }

    private void closeFabMenu() {
        mFabMenu.collapse();
    }

    @Override
    public void itemSelected(Person person) {
        removeFragmentChildren();

        FragmentManager fragmentManager = getChildFragmentManager();
        Fragment currentFragmentInView = fragmentManager.findFragmentById(R.id.fragment_lists_container);
        if (currentFragmentInView != null) {
            fragmentManager.beginTransaction()
                    .remove(currentFragmentInView)
                    .commit();
        }

        View singleItem = LayoutInflater.from(getContext()).inflate(R.layout.view_recycler_view_grid_item, mParentFrameLayout);
        ((ImageView) singleItem.findViewById(R.id.person_photo)).setImageResource(person.mPhotoId);
        ((TextView) singleItem.findViewById(R.id.person_age)).setText(person.mAge);
        ((TextView) singleItem.findViewById(R.id.person_name)).setText(person.mName);
    }

    private void removeFragmentChildren() {
        if (mParentFrameLayout.getChildCount() > 0) {
            mParentFrameLayout.removeAllViews();
        }
    }
}

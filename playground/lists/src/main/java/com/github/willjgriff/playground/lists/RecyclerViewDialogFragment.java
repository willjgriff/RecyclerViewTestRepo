package com.github.willjgriff.playground.lists;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.lists.adapters.PeopleAdapter;
import com.github.willjgriff.playground.lists.adapters.viewholders.PersonViewHolder.PersonItemListener;
import com.github.willjgriff.playground.lists.model.data.People;
import com.github.willjgriff.playground.lists.model.PeopleAdapterPerson;
import com.github.willjgriff.playground.lists.model.Person;

/**
 * Created by Will on 09/02/2016.
 */
public class RecyclerViewDialogFragment extends DialogFragment implements PersonItemListener {

    public interface DialogFragmentListener {
        void itemSelected(Person person);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view_dialog, container);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view_dialog_recycler_view);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new PeopleAdapter(PeopleAdapterPerson.getPeopleAdapterList(People.getPeople()), this));

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void personItemClick(Person person, View transitionImage, View transitionName, View transitionAge) {
        ((DialogFragmentListener) getParentFragment()).itemSelected(person);
        dismiss();
    }

}

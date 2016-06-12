package com.github.willjgriff.playground.lists.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.lists.data.Person;

import java.util.List;

/**
 * Created by Will on 01/02/2016.
 */
public class PeopleRecyclerViewAdapter extends RecyclerView.Adapter<PeopleRecyclerViewAdapter.PersonViewHolder> {

    protected List<Person> mPeople;
    private int mLayoutItem;

    public PeopleRecyclerViewAdapter(List<Person> mPeople) {
        this(mPeople, R.layout.view_recycler_view_item);
    }

    // In reality I think I would create a new recyclerView here instead of passing the layout.
    public PeopleRecyclerViewAdapter(List<Person> people, @LayoutRes int layoutItem) {
        this.mPeople = people;
        this.mLayoutItem = layoutItem;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View personCardView = LayoutInflater.from(parent.getContext()).inflate(mLayoutItem, parent, false);
        return new PersonViewHolder(personCardView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {
        holder.onBind(mPeople.get(position));
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        public CardView mLayoutView;
        public ImageView mPersonImage;
        public TextView mPersonName;
        public TextView mPersonAge;

        public PersonViewHolder(View itemView) {
            super(itemView);
            mLayoutView = (CardView) itemView.findViewById(R.id.fragment_recycler_view_whole_item_view);
            mPersonImage = (ImageView) itemView.findViewById(R.id.person_photo);
            mPersonName = (TextView) itemView.findViewById(R.id.person_name);
            mPersonAge = (TextView) itemView.findViewById(R.id.person_age);
        }

        public void onBind(Person person) {
            mPersonImage.setImageResource(person.mPhotoId);
            mPersonName.setText(person.mName);
            mPersonAge.setText(person.mAge);
            mLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "WASSUP " + person.mName, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

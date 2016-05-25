package com.github.willjgriff.playground.lists.adapters;

import android.content.Context;
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
public class PeopleRecyclerViewAdapter extends RecyclerView.Adapter<PeopleRecyclerViewAdapter.RecyclerViewHolder> {

    private Context mContext;
    protected List<Person> mPeople;
    private int mLayoutItem;

    public PeopleRecyclerViewAdapter(Context context, List<Person> mPeople) {
        this(context, mPeople, R.layout.view_recycler_view_item);
    }

    // In reality I think I would create a new recyclerView here instead of passing the layout.
    public PeopleRecyclerViewAdapter(Context context, List<Person> people, @LayoutRes int layoutItem) {
        this.mContext = context;
        this.mPeople = people;
        this.mLayoutItem = layoutItem;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View personCardView = LayoutInflater.from(parent.getContext()).inflate(mLayoutItem, parent, false);
        return new RecyclerViewHolder(personCardView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.mPersonImage.setImageResource(mPeople.get(position).mPhotoId);
        holder.mPersonName.setText(mPeople.get(position).mName);
        holder.mPersonAge.setText(mPeople.get(position).mAge);
        holder.mLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "WASSUP " + mPeople.get(position).mName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public CardView mLayoutView;
        public ImageView mPersonImage;
        public TextView mPersonName;
        public TextView mPersonAge;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mLayoutView = (CardView) itemView.findViewById(R.id.fragment_recycler_view_whole_item_view);
            mPersonImage = (ImageView) itemView.findViewById(R.id.person_photo);
            mPersonName = (TextView) itemView.findViewById(R.id.person_name);
            mPersonAge = (TextView) itemView.findViewById(R.id.person_age);
        }
    }

}

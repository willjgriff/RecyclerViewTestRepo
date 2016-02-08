package com.example.will.Playground.ListViewTests;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.will.Playground.R;

import java.util.List;

/**
 * Created by Will on 01/02/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Person> mPeople;
    private int mLayoutItem;

    // In reality I wouldn't pass a layout reference here, but for playing purpose it's okay.
    public RecyclerViewAdapter(List<Person> mPeople, @LayoutRes int layoutItem) {
        this.mPeople = mPeople;
        this.mLayoutItem = layoutItem;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View personCardView = LayoutInflater.from(parent.getContext()).inflate(mLayoutItem, parent, false);
        return new RecyclerViewHolder(personCardView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.mPersonImage.setImageResource(mPeople.get(position).mPhotoId);
        holder.mPersonName.setText(mPeople.get(position).mName);
        holder.mPersonAge.setText(mPeople.get(position).mAge);
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public ImageView mPersonImage;
        public TextView mPersonName;
        public TextView mPersonAge;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mPersonImage = (ImageView) itemView.findViewById(R.id.person_photo);
            mPersonName = (TextView) itemView.findViewById(R.id.person_name);
            mPersonAge = (TextView) itemView.findViewById(R.id.person_age);
        }
    }

}

package com.github.willjgriff.playground.lists.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.lists.adapters.viewholders.HeaderViewHolder;
import com.github.willjgriff.playground.lists.adapters.viewholders.AbstractPeopleViewHolder;
import com.github.willjgriff.playground.lists.adapters.viewholders.PersonViewHolder;
import com.github.willjgriff.playground.lists.adapters.viewholders.PersonViewHolder.PersonItemListener;
import com.github.willjgriff.playground.lists.model.PeopleAdapterModel;
import com.github.willjgriff.playground.lists.model.PeopleAdapterModel.PeopleItemType;

import java.util.List;

import static com.github.willjgriff.playground.lists.model.PeopleAdapterModel.PeopleItemType.getPeopleViewHolderType;

/**
 * Created by Will on 01/02/2016.
 */
public class PeopleAdapter extends RecyclerView.Adapter<AbstractPeopleViewHolder<PeopleAdapterModel>> {

    protected List<PeopleAdapterModel> mPeople;
    private PersonItemListener mPersonItemListener;

    public PeopleAdapter(List<PeopleAdapterModel> people, PersonItemListener personItemListener) {
        mPeople = people;
        mPersonItemListener = personItemListener;
    }

    @Override
    public int getItemViewType(int position) {
        return mPeople.get(position).getItemType().ordinal();
    }

    @Override
    public AbstractPeopleViewHolder<PeopleAdapterModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        PeopleItemType type = getPeopleViewHolderType(viewType);
        AbstractPeopleViewHolder viewHolder = null;
        switch (type) {
            case HEADER:
                viewHolder = new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_view_header, parent, false));
                break;
            case PERSON:
                viewHolder = new PersonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_view_item, parent, false), mPersonItemListener);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AbstractPeopleViewHolder<PeopleAdapterModel> holder, int position) {
        holder.onBind(mPeople.get(position));
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

}

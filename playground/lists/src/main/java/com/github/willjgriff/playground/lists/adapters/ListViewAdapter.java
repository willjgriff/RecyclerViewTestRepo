package com.github.willjgriff.playground.lists.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.lists.model.Person;

import java.util.List;

/**
 * Created by Will on 02/02/2016.
 */
public class ListViewAdapter extends ArrayAdapter<Person> {

    private Context mContext;
    private List<Person> mPeople;

    public ListViewAdapter(Context context, List<Person> people) {
        super(context, -1, people);
        this.mContext = context;
        this.mPeople = people;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItem = layoutInflater.inflate(R.layout.view_list_view_item, parent, false);

        Person person = mPeople.get(position);

        ((TextView)listItem.findViewById(R.id.fragmnet_list_view_item_person_age)).setText(person.mAge);
        ((TextView)listItem.findViewById(R.id.fragmnet_list_view_item_person_name)).setText(person.mName);
        ((ImageView)listItem.findViewById(R.id.fragment_list_view_item_person_photo)).setImageResource(person.mPhotoId);

        return listItem;
    }


}

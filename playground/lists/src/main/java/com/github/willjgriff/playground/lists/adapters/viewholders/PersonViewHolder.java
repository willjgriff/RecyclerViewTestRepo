package com.github.willjgriff.playground.lists.adapters.viewholders;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.lists.model.PeopleAdapterPerson;
import com.github.willjgriff.playground.lists.model.Person;

/**
 * Created by Will on 12/06/2016.
 */
public class PersonViewHolder extends AbstractPeopleViewHolder<PeopleAdapterPerson> {

    private CardView mLayoutView;
    private ImageView mPersonImage;
    private TextView mPersonName;
    private TextView mPersonAge;
    private PersonItemListener mPersonItemListener;

    public PersonViewHolder(View itemView, PersonItemListener listener) {
        super(itemView);
        mLayoutView = (CardView) itemView.findViewById(R.id.fragment_recycler_view_whole_item_view);
        mPersonImage = (ImageView) itemView.findViewById(R.id.person_photo);
        mPersonName = (TextView) itemView.findViewById(R.id.person_name);
        mPersonAge = (TextView) itemView.findViewById(R.id.person_age);
        mPersonItemListener = listener;
    }

    @Override
    public void onBind(PeopleAdapterPerson peopleAdapterPerson) {
        mPersonImage.setImageResource(peopleAdapterPerson.getModel().mPhotoId);
        mPersonName.setText(peopleAdapterPerson.getModel().mName);
        mPersonAge.setText(peopleAdapterPerson.getModel().mAge);
        mLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPersonItemListener.personItemClick(peopleAdapterPerson.getModel(), mPersonImage, mPersonName, mPersonAge);
            }
        });
    }

    public interface PersonItemListener {
        void personItemClick(Person person, View transitionImage, View transitionName, View transitionAge);
    }
}

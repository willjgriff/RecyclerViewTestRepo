package com.github.willjgriff.playground.lists.model;

/**
 * Created by Will on 12/06/2016.
 */

public class PeopleAdapterHeader extends PeopleAdapterModel<PeopleAdapterHeader> {

    public PeopleAdapterHeader() {
        super(null);
    }

    @Override
    public PeopleItemType getItemType() {
        return PeopleItemType.HEADER;
    }
}

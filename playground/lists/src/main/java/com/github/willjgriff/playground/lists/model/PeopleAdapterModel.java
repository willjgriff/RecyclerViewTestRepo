package com.github.willjgriff.playground.lists.model;

/**
 * Created by Will on 12/06/2016.
 */

public abstract class PeopleAdapterModel<MODEL> {

    MODEL mModel;

    PeopleAdapterModel(MODEL model) {
        mModel = model;
    }

    public MODEL getModel() {
        return mModel;
    }

    public abstract PeopleItemType getItemType();

    public enum PeopleItemType {
        HEADER,
        PERSON;

        public int getPeopleViewHolderOrdinal() {
            return ordinal();
        }

        public static PeopleItemType getPeopleViewHolderType(int ordinal) {
            return values()[ordinal];
        }
    }
}

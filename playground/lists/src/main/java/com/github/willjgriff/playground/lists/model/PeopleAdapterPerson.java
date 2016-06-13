package com.github.willjgriff.playground.lists.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 12/06/2016.
 */

public class PeopleAdapterPerson extends PeopleAdapterModel<Person> {

    public PeopleAdapterPerson(Person person) {
        super(person);
    }

    @Override
    public PeopleItemType getItemType() {
        return PeopleItemType.PERSON;
    }

    public static List<PeopleAdapterModel> getPeopleAdapterList(List<Person> people) {
        List<PeopleAdapterModel> peopleAdapterList = new ArrayList<>();
        for (Person person : people) {
            peopleAdapterList.add(new PeopleAdapterPerson(person));
        }
        return peopleAdapterList;
    }
}

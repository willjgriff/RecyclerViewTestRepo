package com.example.will.recyclerviewtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 31/01/2016.
 */
public class People {


    public static List<Person> getPeople() {
        List<Person> mPeople = new ArrayList<>();
        if (mPeople.isEmpty()) {
            mPeople.add(new Person("Will", "25", R.drawable.red_panda));
            mPeople.add(new Person("Josh", "26", R.drawable.red_panda));
            mPeople.add(new Person("Rob", "24", R.drawable.red_panda));
            mPeople.add(new Person("Will", "25", R.drawable.red_panda));
            mPeople.add(new Person("Josh", "26", R.drawable.red_panda));
            mPeople.add(new Person("Rob", "24", R.drawable.red_panda));
            mPeople.add(new Person("Will", "25", R.drawable.red_panda));
            mPeople.add(new Person("Josh", "26", R.drawable.red_panda));
            mPeople.add(new Person("Rob", "24", R.drawable.red_panda));
            mPeople.add(new Person("Will", "25", R.drawable.red_panda));
            mPeople.add(new Person("Josh", "26", R.drawable.red_panda));
            mPeople.add(new Person("Rob", "24", R.drawable.red_panda));
            mPeople.add(new Person("Will", "25", R.drawable.red_panda));
            mPeople.add(new Person("Josh", "26", R.drawable.red_panda));
            mPeople.add(new Person("Rob", "24", R.drawable.red_panda));
        }
        return mPeople;
    }
}

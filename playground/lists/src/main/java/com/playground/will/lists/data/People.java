package com.playground.will.lists.data;

import com.example.will.Playground.R;

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
            mPeople.add(new Person("Will and co and ro", "25", R.drawable.red_panda));
            mPeople.add(new Person("Josh", "26", R.drawable.red_panda));
            mPeople.add(new Person("Rob", "24", R.drawable.red_panda));
            mPeople.add(new Person("Will", "25", R.drawable.red_panda));
            mPeople.add(new Person("Joshs funny socks and whats", "26", R.drawable.red_panda));
            mPeople.add(new Person("Rob", "24", R.drawable.red_panda));
            mPeople.add(new Person("Will", "25", R.drawable.red_panda));
            mPeople.add(new Person("Josh", "26", R.drawable.red_panda));
            mPeople.add(new Person("Robby shocking roundabout", "24", R.drawable.red_panda));
            mPeople.add(new Person("Will", "25", R.drawable.red_panda));
            mPeople.add(new Person("Josh", "26", R.drawable.red_panda));
            mPeople.add(new Person("Rob", "24", R.drawable.red_panda));
        }
        return mPeople;
    }

    public static String[] mNames = new String[]{
            "Jigga bang",
            "Bam Dook",
            "Yipshlada",
            "Es La Nouve",
            "Jigga bang",
            "Bam Dook",
            "Yipshlada",
            "Es La Nouve",
            "Jigga bang",
            "Bam Dook",
            "Yipshlada",
            "Es La Nouve",
            "Jigga bang",
            "Bam Dook",
            "Yipshlada",
            "Es La Nouve"
    };

    public static List<String> getNames() {
        List<String> mNames = new ArrayList<>();
        mNames.add("Jigga bang");
        mNames.add("Bam Dook");
        mNames.add("Yipshlada");
        mNames.add("Es La Nouve");
        mNames.add("Jigga bang");
        mNames.add("Bam Dook");
        mNames.add("Yipshlada");
        mNames.add("Es La Nouve");
        mNames.add("Jigga bang");
        mNames.add("Bam Dook");
        mNames.add("Yipshlada");
        mNames.add("Es La Nouve");
        mNames.add("Jigga bang");
        mNames.add("Bam Dook");
        mNames.add("Yipshlada");
        mNames.add("Es La Nouve");
        mNames.add("Jigga bang");
        mNames.add("Bam Dook");
        mNames.add("Yipshlada");
        mNames.add("Es La Nouve");
        return mNames;
    }
}

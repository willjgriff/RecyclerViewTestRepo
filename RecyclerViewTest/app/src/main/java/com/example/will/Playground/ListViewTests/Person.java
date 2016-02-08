package com.example.will.Playground.ListViewTests;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Will on 31/01/2016.
 */
public class Person implements Parcelable {

    String mName;
    String mAge;
    int mPhotoId;

    Person (String name, String age, int photoId) {
        this.mName = name;
        this.mAge = age;
        this.mPhotoId = photoId;
    }

    protected Person(Parcel in) {
        mName = in.readString();
        mAge = in.readString();
        mPhotoId = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mAge);
        dest.writeInt(mPhotoId);
    }
}




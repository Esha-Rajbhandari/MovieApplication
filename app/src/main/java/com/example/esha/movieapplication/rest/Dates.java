package com.example.esha.movieapplication.rest;

import android.os.Parcel;
import android.os.Parcelable;

public class Dates implements Parcelable{
    private String maximum;
    private String minimum;

    protected Dates(Parcel in) {
        maximum = in.readString();
        minimum = in.readString();
    }

    public static final Creator<Dates> CREATOR = new Creator<Dates>() {
        @Override
        public Dates createFromParcel(Parcel in) {
            return new Dates(in);
        }

        @Override
        public Dates[] newArray(int size) {
            return new Dates[size];
        }
    };

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(maximum);
        parcel.writeString(minimum);
    }
}

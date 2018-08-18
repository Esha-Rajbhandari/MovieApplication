package com.example.esha.movieapplication.rest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieVideoList implements Parcelable{
    @SerializedName("id")
    private Integer id;

    @SerializedName("results")
    private ArrayList<VideoResults> results=new ArrayList<>();

    protected MovieVideoList(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
    }

    public static final Creator<MovieVideoList> CREATOR = new Creator<MovieVideoList>() {
        @Override
        public MovieVideoList createFromParcel(Parcel in) {
            return new MovieVideoList(in);
        }

        @Override
        public MovieVideoList[] newArray(int size) {
            return new MovieVideoList[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<VideoResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<VideoResults> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
    }
}

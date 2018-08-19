package com.example.esha.movieapplication.rest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieReviewList implements Parcelable{

    @SerializedName("id")
    private Integer id;

    @SerializedName("page")
    private Integer page;

    @SerializedName("results")
    private ArrayList<ReviewResult> results = new ArrayList<>();

    @SerializedName("total_pages")
    private Integer total_pages;

    @SerializedName("total_results")
    private Integer total_results;

    protected MovieReviewList(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            page = null;
        } else {
            page = in.readInt();
        }
        if (in.readByte() == 0) {
            total_pages = null;
        } else {
            total_pages = in.readInt();
        }
        if (in.readByte() == 0) {
            total_results = null;
        } else {
            total_results = in.readInt();
        }
    }

    public static final Creator<MovieReviewList> CREATOR = new Creator<MovieReviewList>() {
        @Override
        public MovieReviewList createFromParcel(Parcel in) {
            return new MovieReviewList(in);
        }

        @Override
        public MovieReviewList[] newArray(int size) {
            return new MovieReviewList[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<ReviewResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<ReviewResult> results) {
        this.results = results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
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
        if (page == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(page);
        }
        if (total_pages == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(total_pages);
        }
        if (total_results == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(total_results);
        }
    }
}

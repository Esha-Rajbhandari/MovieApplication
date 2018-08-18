package com.example.esha.movieapplication.rest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ReviewResult implements Parcelable{
    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Creator<ReviewResult> getCREATOR() {
        return CREATOR;
    }

    protected ReviewResult(Parcel in) {
        id = in.readString();
        author = in.readString();
        content = in.readString();
    }

    public static final Creator<ReviewResult> CREATOR = new Creator<ReviewResult>() {
        @Override
        public ReviewResult createFromParcel(Parcel in) {
            return new ReviewResult(in);
        }

        @Override
        public ReviewResult[] newArray(int size) {
            return new ReviewResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(author);
        parcel.writeString(content);
    }
}

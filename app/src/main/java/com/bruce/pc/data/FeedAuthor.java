package com.bruce.pc.data;

import android.os.Parcel;
import android.os.Parcelable;

public class FeedAuthor implements Parcelable {
    public static final Creator<FeedAuthor> CREATOR = new Creator<FeedAuthor>() {
        @Override
        public FeedAuthor createFromParcel(Parcel source) {
            FeedAuthor var = new FeedAuthor();
            var.name = source.readString();
            return var;
        }

        @Override
        public FeedAuthor[] newArray(int size) {
            return new FeedAuthor[size];
        }
    };
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

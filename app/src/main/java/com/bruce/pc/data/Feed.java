package com.bruce.pc.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Feed implements Parcelable {
    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel source) {
            Feed var = new Feed();
            var.summary = source.readString();
            var.encoded_title = source.readString();
            var.author = source.readParcelable(FeedAuthor.class.getClassLoader());
            var.insight_summary = source.readString();
            var.title = source.readString();
            var.featured_image = source.readString();
            var.url = source.readString();
            var.content = source.readString();
            var.date_modified = source.readString();
            var.date_published = source.readString();
            var.id = source.readString();
            var.category = source.readString();
            var.summary_html = source.readString();
            var.content_html = source.readString();
            return var;
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };
    private String summary;
    private String encoded_title;
    private FeedAuthor author;
    private String insight_summary;
    private String title;
    private String featured_image;
    private String url;
    private String content;
    private String date_modified;
    private String date_published;
    private String id;
    private String category;
    private String summary_html;
    private String content_html;

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getEncoded_title() {
        return this.encoded_title;
    }

    public void setEncoded_title(String encoded_title) {
        this.encoded_title = encoded_title;
    }

    public FeedAuthor getAuthor() {
        return this.author;
    }

    public void setAuthor(FeedAuthor author) {
        this.author = author;
    }

    public String getInsight_summary() {
        return this.insight_summary;
    }

    public void setInsight_summary(String insight_summary) {
        this.insight_summary = insight_summary;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeatured_image() {
        return this.featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_modified() {
        return this.date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getDate_published() {
        return this.date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSummary_html() {
        return this.summary_html;
    }

    public void setSummary_html(String summary_html) {
        this.summary_html = summary_html;
    }

    public String getContent_html() {
        return this.content_html;
    }

    public void setContent_html(String content_html) {
        this.content_html = content_html;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.summary);
        dest.writeString(this.encoded_title);
        dest.writeParcelable(this.author, flags);
        dest.writeString(this.insight_summary);
        dest.writeString(this.title);
        dest.writeString(this.featured_image);
        dest.writeString(this.url);
        dest.writeString(this.content);
        dest.writeString(this.date_modified);
        dest.writeString(this.date_published);
        dest.writeString(this.id);
        dest.writeString(this.category);
        dest.writeString(this.summary_html);
        dest.writeString(this.content_html);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

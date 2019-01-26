package com.bruce.pc.blogfeeds;

import com.bruce.pc.data.Feed;

import java.util.ArrayList;
import java.util.List;

public class BlogFeedsViewState implements Cloneable {
    private boolean loading;
    private List<List<Feed>> feeds;

    public BlogFeedsViewState(boolean loading, List<List<Feed>> feeds) {
        this.loading = loading;
        this.feeds = feeds;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public List<List<Feed>> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<List<Feed>> feeds) {
        this.feeds = feeds;
    }

    public BlogFeedsViewState clone() {
        BlogFeedsViewState result = null;
        try {
            result = (BlogFeedsViewState) super.clone();
            result.feeds = feeds == null ? null : new ArrayList<>(feeds);
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable

        }
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + " feeds size " + ((feeds == null) ? 0 : feeds.size());
    }
}

package com.bruce.pc.blogfeeds;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bruce.pc.data.Feed;

import java.util.List;

public abstract class AbstractFeedViewHolder extends RecyclerView.ViewHolder {
    protected FeedClickListener listener;

    public AbstractFeedViewHolder(@NonNull View itemView, FeedClickListener listener) {
        super(itemView);
        this.listener = listener;
    }

    public abstract void bind(List<Feed> feeds);
}

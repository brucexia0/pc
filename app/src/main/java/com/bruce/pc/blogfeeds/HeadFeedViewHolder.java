package com.bruce.pc.blogfeeds;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bruce.pc.data.Feed;
import com.squareup.picasso.Picasso;

import java.util.List;

class HeadFeedViewHolder extends AbstractFeedViewHolder {
    HeadFeedCardView feedCardView;

    public HeadFeedViewHolder(View itemView, FeedClickListener listener) {
        super(itemView, listener);
        feedCardView = (HeadFeedCardView) itemView;
    }

    @Override
    public void bind(List<Feed> feeds) {
        Feed feed = feeds.get(0);
        Picasso.with(itemView.getContext()).load(feed.getFeatured_image()).into(feedCardView.getImageView());
        feedCardView.getTitleView().setText(feed.getTitle());
        feedCardView.getDescView().setText(feed.getTitle());
        feedCardView.setOnClickListener(v -> listener.onClick(getAdapterPosition(), feed));
    }
}

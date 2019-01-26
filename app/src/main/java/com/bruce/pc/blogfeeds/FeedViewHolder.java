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

class FeedViewHolder extends AbstractFeedViewHolder {
    public FeedViewHolder(@NonNull View itemView, FeedClickListener listener) {
        super(itemView, listener);
    }

    public void bind(List<Feed> feeds) {
        int count = ((ViewGroup) itemView).getChildCount();
        for (int i = 0; i < count; i++) {
            if (i < feeds.size()) {
                FeedCardView view = (FeedCardView) ((ViewGroup) itemView).getChildAt(i);
                Feed feed = feeds.get(i);
                Picasso.with(itemView.getContext()).load(feed.getFeatured_image()).into(view.getImageView());
                view.getTitleView().setText(feed.getTitle());
                itemView.setOnClickListener(v -> listener.onClick(getAdapterPosition(), feed));
            }
        }
    }

}

package com.bruce.pc.blogfeeds;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bruce.pc.data.Feed;

import java.util.List;

class FeedAdapter extends RecyclerView.Adapter<AbstractFeedViewHolder> {
    private Context mainActivity;
    FeedClickListener listener;
    int columnCount;

    public FeedAdapter(Context mainActivity, FeedClickListener listener, int columnCount) {
        this.mainActivity = mainActivity;
        this.listener = listener;
        this.columnCount = columnCount;
    }

    List<List<Feed>> data;

    @NonNull
    @Override
    public AbstractFeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        if (i == 0) {
            HeadFeedCardView view = new HeadFeedCardView(viewGroup.getContext());
            view.setLayoutParams(layoutParams);
            return new HeadFeedViewHolder(view, listener);
        } else {
            View view = ViewFactory.newFeedItemRow(viewGroup.getContext(), columnCount);
            view.setLayoutParams(layoutParams);
            return new FeedViewHolder(view, listener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractFeedViewHolder feedViewHolder, int position) {
        feedViewHolder.bind(data.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).size() == 1) return 0;
        return 1;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}

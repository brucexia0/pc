package com.bruce.pc.blogfeeds;

import com.bruce.pc.data.Feed;
import com.bruce.pc.domain.Event;

import java.util.List;

public class FeedsEvent extends Event implements Cloneable {
    public static class NewFeedsEffect extends FeedsEvent {
        private List<Feed> feeds;

        public NewFeedsEffect(List<Feed> feeds) {
            this.feeds = feeds;
        }

        public List<Feed> getFeeds() {
            return feeds;
        }
    }
}

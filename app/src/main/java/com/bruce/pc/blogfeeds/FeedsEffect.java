package com.bruce.pc.blogfeeds;

import com.bruce.pc.data.Feed;
import com.bruce.pc.domain.Effect;
import com.bruce.pc.domain.Event;

import java.util.List;

public class FeedsEffect extends Effect {
    public static FeedsEffect reload = new FeedsEffect();
    public static FeedsEffect initialLoad = new FeedsEffect();

}

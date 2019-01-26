package com.bruce.pc.blogfeeds;

import android.arch.lifecycle.ViewModel;

import com.bruce.pc.core.Logger;
import com.bruce.pc.data.Api;
import com.bruce.pc.data.Feed;
import com.bruce.pc.domain.Effect;
import com.bruce.pc.domain.Event;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

public class BlogFeedsViewModel extends ViewModel {
//    @Inject
//    public BlogFeedsViewModel(Api api) {
//        this.api = api;
//    }

    public BlogFeedsViewModel() {
        super();
        init();
    }

    @Inject
    Api api;
    private Observable<BlogFeedsViewState> viewState;
    private PublishRelay<Event> events = PublishRelay.create();
    private PublishRelay<Effect> effects = PublishRelay.create();

    private Logger logger = new Logger();

    void init() {
        Observable<Event> effectsHandler = effects.switchMap(effect -> effectToEvent(effect));
        viewState = Observable.merge(events, effectsHandler)
                .doOnNext(event -> logger.debug("got event " + event.toString(), null))
                .scan(new BlogFeedsViewState(false, null), (state, event) -> reduce(state, event))
                .doOnNext(state -> logger.debug("got state " + state.toString(), null))
                .replay(1);
        ((ConnectableObservable<BlogFeedsViewState>) viewState).connect();
    }

    public Observable<BlogFeedsViewState> getViewState() {
        return viewState;
    }

    public void onEvent(Event event) {
        events.accept(event);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private Observable<? extends Event> effectToEvent(Effect effect) {
        if (effect == FeedsEffect.reload
                || effect == FeedsEffect.initialLoad) {
            return api.getFeeeds().map(feedResponse -> feedResponse.getItems())
                    .map(fi -> foldFeeds(fi))
                    .map(feedItems -> new FeedsEvent.NewFeedsEffect(feedItems))
                    .subscribeOn(Schedulers.newThread())
                    .toObservable();
        } else return null;
    }

    private BlogFeedsViewState reduce(BlogFeedsViewState state, Event event) {
        BlogFeedsViewState newState = state.clone();
        if (event instanceof FeedsEvent.NewFeedsEffect) {
            newState.setFeeds(((FeedsEvent.NewFeedsEffect) event).getFeeds());
            newState.setLoading(false);
        } else if (event == FeedsEvent.initialLoad || event == FeedsEvent.reload) {
            newState.setLoading(true);
            effects.accept(FeedsEffect.reload);
        }
        return newState;
    }

    private List<List<Feed>> foldFeeds(List<Feed> feeds) {
        List<List<Feed>> result = new ArrayList<>();
        List<Feed> row = null;
        for (int i = 0; i < feeds.size(); i++) {
            Feed feed = feeds.get(i);
            if (i == 0 || (i - 1) % 3 == 0) {
                row = new ArrayList<>();
                result.add(row);
            }
            row.add(feed);
        }
        return result;
    }

}

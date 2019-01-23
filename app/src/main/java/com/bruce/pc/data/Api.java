package com.bruce.pc.data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface Api {
    @GET("blog/feed/json")
    Single<FeedResponse> getFeeeds();
}

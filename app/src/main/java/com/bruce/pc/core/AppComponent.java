package com.bruce.pc.core;

import com.bruce.pc.blogfeeds.BlogFeedsViewModel;
import com.bruce.pc.data.AppModule;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    public void inject(BlogFeedsViewModel viewModel);
}

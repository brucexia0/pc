package com.bruce.pc.core;

import android.app.Application;

import com.bruce.pc.blogfeeds.BlogFeedsViewModel;
import com.bruce.pc.data.AppModule;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    public void inject(BlogFeedsViewModel viewModel);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

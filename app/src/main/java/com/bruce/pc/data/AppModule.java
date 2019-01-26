package com.bruce.pc.data;

import com.bruce.pc.blogfeeds.BlogFeedsViewModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class AppModule {
    @Provides
    ObjectMapper objectMapper() {
        return new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Provides
    Retrofit retrofit(ObjectMapper mapper) {
        return new Retrofit.Builder()
//                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()).build())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://www.personalcapital.com/")
                .build();
    }

    @Provides
    Api api(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}

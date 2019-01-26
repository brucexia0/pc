package com.bruce.pc.blogfeeds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.bruce.pc.R;
import com.bruce.pc.data.Feed;

public class FeedActivity extends AppCompatActivity {

    private static String KEY_FEED = "feed";

    public static void openFeed(Context context, Feed feed) {
        Intent intent = new Intent(context, FeedActivity.class);
        intent.putExtra(KEY_FEED, feed);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        setContentView(webView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Feed feed = getIntent().getParcelableExtra(KEY_FEED);
        webView.loadUrl(feed.getUrl());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}

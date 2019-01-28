package com.bruce.pc.blogfeeds;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bruce.pc.MyApp;
import com.bruce.pc.R;
import com.bruce.pc.core.Logger;
import com.bruce.pc.data.Feed;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    BlogFeedsViewModel viewModel;
    CompositeDisposable startStopDisposables = new CompositeDisposable();

    protected void addStartStopDisposable(Disposable d) {
        startStopDisposables.add(d);
    }

    ViewFactory factory = new ViewFactory(this);
    RecyclerView recyclerView;
    FeedAdapter adapter;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This is hack, so I don't have to spend time on setting up dagger android or create viewmodel factory etc.
        viewModel = ViewModelProviders.of(this).get(BlogFeedsViewModel.class);
        ((MyApp) getApplication()).appComponent().inject(viewModel);
        viewModel.init();
        LinearLayout rootView = new LinearLayout(this);
        rootView.setOrientation(LinearLayout.VERTICAL);
        swipeRefreshLayout = new SwipeRefreshLayout(this);
        rootView.addView(swipeRefreshLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView = factory.newFeedsRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.addView(recyclerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(rootView);
        getSupportActionBar().setTitle("Personal Capital");

        adapter = new FeedAdapter(this, new FeedClickListener() {
            @Override
            public void onClick(int index, Feed feed) {
                openFeed(feed);
            }
        }, getResources().getInteger(R.integer.columns));
        recyclerView.setAdapter(adapter);
        if (savedInstanceState == null) viewModel.onEvent(FeedsEvent.initialLoad);
    }


    private void openFeed(Feed feed) {
        FeedActivity.openFeed(this, feed);
    }

    Logger logger = new Logger();

    @Override
    protected void onStart() {
        super.onStart();
        Disposable disposable = viewModel.getViewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(state -> render(state),
                        throwable -> {
                            throwable.printStackTrace();
                        });
        addStartStopDisposable(disposable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        startStopDisposables.clear();
    }

    private void render(BlogFeedsViewState state) {
        swipeRefreshLayout.setRefreshing(state.isLoading());
        adapter.data = state.getFeeds();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reload) {
            viewModel.onEvent(FeedsEvent.reload);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

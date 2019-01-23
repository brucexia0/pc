package com.bruce.pc.blogfeeds;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bruce.pc.MyApp;
import com.bruce.pc.R;
import com.bruce.pc.core.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    BlogFeedsViewModel viewModel;
    CompositeDisposable startStopDisposables = new CompositeDisposable();

    protected void addStartStopDisposable(Disposable d) {
        startStopDisposables.add(d);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(BlogFeedsViewModel.class);
        ((MyApp) getApplication()).appComponent().inject(viewModel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if (savedInstanceState != null) viewModel.onEvent(FeedsEvent.initialLoad);
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
        logger.debug("show state " + state.toString(), null);
        showTest("render");
    }

    private void showTest(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

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

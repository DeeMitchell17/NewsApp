package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<List<Movie>> {

    public static final String LOG_TAG = MovieActivity.class.getName();

    private static final String GUARDIAN_URL = "https://content.guardianapis.com/search?show-tags=contributor&q=%22netflix%20original%22&api-key=4e6e43a1-a188-4bc7-985f-c1c944a35852";

    private static final int MOVIE_LOADER_ID = 1;

    private MovieAdapter mAdapter;

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView movieListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        movieListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());

        movieListView.setAdapter(mAdapter);

        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Movie currentMovie = mAdapter.getItem(position);

                Uri movieUri = Uri.parse(currentMovie.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, movieUri);

                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new MovieLoader(this, GUARDIAN_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_articles_found);
        mAdapter.clear();

        if (movies != null && !movies.isEmpty()) {
            mAdapter.addAll(movies);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        mAdapter.clear();
    }
}
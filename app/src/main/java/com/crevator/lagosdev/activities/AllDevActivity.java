package com.crevator.lagosdev.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.crevator.lagosdev.Model.Developer;
import com.crevator.lagosdev.R;
import com.crevator.lagosdev.adapters.DevelopersAdapter;
import com.crevator.lagosdev.interfaces.AllDevContract;
import com.crevator.lagosdev.presenters.AllDevPresenter;
import com.crevator.lagosdev.util.Data;
import com.crevator.lagosdev.util.LagosDevRecycler;

import java.util.List;

public class AllDevActivity extends AppCompatActivity implements AllDevContract.View, LagosDevRecycler.ClickListener {
    AllDevContract.Presenter mPresenter;
    RecyclerView mDevelopersRecycler;
    ProgressBar mDevelopersProgressBar;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_dev);
        //initialize view resources
        init();
        //initialize the AllDev presenter
        new AllDevPresenter(this);
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        mDevelopersProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.retrieveAllDevelopers();
            }
        });
        mDevelopersRecycler = (RecyclerView) findViewById(R.id.devlist);
        mDevelopersRecycler.setLayoutManager(new LinearLayoutManager(this));
        mDevelopersRecycler.addOnItemTouchListener(new LagosDevRecycler.
                RecyclerTouchListener(getApplicationContext(), mDevelopersRecycler, this));
    }

    @Override
    public void setPresenter(AllDevContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setDevelopersAdapter(DevelopersAdapter developersAdapter) {
        mDevelopersRecycler.setAdapter(developersAdapter);
    }

    @Override
    public void showDeveloperDetails(Developer developer) {
        Intent i = new Intent(AllDevActivity.this, ProfileActivity.class);
        i.putExtra(Data.PROFILE_DETAILS, developer);
        showMessage(developer.getUserName());
        startActivity(i);
    }


    @Override
    public void showLoading(boolean show) {
        mDevelopersProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mSwipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view, int position) {
        mPresenter.OnShowDeveloperDetails(position);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

}

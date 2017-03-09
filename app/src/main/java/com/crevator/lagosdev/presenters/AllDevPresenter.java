package com.crevator.lagosdev.presenters;

import android.app.Activity;
import android.os.AsyncTask;

import com.crevator.lagosdev.LagosDevApp;
import com.crevator.lagosdev.Model.Developer;
import com.crevator.lagosdev.adapters.DevelopersAdapter;
import com.crevator.lagosdev.interfaces.AllDevContract;
import com.crevator.lagosdev.rest.ApiService;
import com.crevator.lagosdev.rest.DevelopersResponse;
import com.crevator.lagosdev.util.GsonUtils;
import com.crevator.lagosdev.util.SharedPreferenceHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adetuyi Tolu on 3/4/2017.
 */


public class AllDevPresenter implements AllDevContract.Presenter {
    private AllDevContract.View mView;
    private ApiService lagosDevApi;
    private List<Developer> mDeveloperList;
    private DevelopersAdapter mDevelopersAdapter;

    public AllDevPresenter(AllDevContract.View view) {
        mView = view;
        view.setPresenter(this);
        lagosDevApi = LagosDevApp.getInstance().getApiService();
        mDeveloperList = new ArrayList<>();
        setDevelopersAdapter();
        retrieveAllDevelopers();
    }

    public void setDevelopersAdapter() {
        mDevelopersAdapter = new DevelopersAdapter(mDeveloperList, (Activity) getView());
        mView.setDevelopersAdapter(mDevelopersAdapter);
    }

    @Override
    public void retrieveAllDevelopers() {
        if (LagosDevApp.getInstance().isOnline((Activity) getView())) {
            fetchDevelopersOnline();
        } else {
            fetchDevelopersLocally();
        }
    }


    @Override
    public void OnShowDeveloperDetails(int position) {
        mView.showDeveloperDetails(mDeveloperList.get(position));
    }

    @Override
    public AllDevContract.View getView() {
        return mView;
    }

    @Override
    public void onStart() {
        mView.setPresenter(this);
    }

    void saveDevelopersDetails(final DevelopersResponse developersResponse) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SharedPreferenceHandler.saveDeveloperRecords((Activity) getView(), developersResponse);
            }
        };
        AsyncTask.execute(runnable);

    }

    void fetchDevelopersLocally() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mDeveloperList = SharedPreferenceHandler.
                        fetchDeveloperRecords((Activity) getView()).developers;
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                popultateDevelopersList();
            }
        }.execute();
    }

    void fetchDevelopersOnline() {
        mView.showLoading(true);
        lagosDevApi.getAllDevelopers("location:lagos").enqueue(new Callback<DevelopersResponse>() {
            @Override
            public void onResponse(Call<DevelopersResponse> call, Response<DevelopersResponse> response) {
                mDeveloperList = new ArrayList<>();
                mDeveloperList = response.body().developers;
                if (!mDeveloperList.isEmpty()) {
                    saveDevelopersDetails(response.body());
                    popultateDevelopersList();
                }
                mView.showLoading(false);
            }

            @Override
            public void onFailure(Call<DevelopersResponse> call, Throwable t) {
                mView.showMessage(t.getMessage());
                mView.showLoading(false);
            }
        });
    }

    void popultateDevelopersList() {
        if (mDeveloperList.size() < 1) {
            mView.showMessage("Developers list is empty");
        } else {
            mDevelopersAdapter = new DevelopersAdapter(mDeveloperList, (Activity) getView());
            mView.setDevelopersAdapter(mDevelopersAdapter);
            //mDevelopersAdapter.notifyDataSetChanged();
        }
    }
}

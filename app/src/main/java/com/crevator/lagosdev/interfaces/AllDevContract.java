package com.crevator.lagosdev.interfaces;

import com.crevator.lagosdev.Model.Developer;
import com.crevator.lagosdev.adapters.DevelopersAdapter;

import java.util.List;

/**
 * Created by Adetuyi Tolu on 3/4/2017.
 */


public class AllDevContract {
    public interface View extends BaseView<Presenter> {
        void setDevelopersAdapter(DevelopersAdapter developersAdapter);

        void showDeveloperDetails(Developer developer);

    }

    public interface Presenter extends BasePresenter<View> {
        void retrieveAllDevelopers();

        void OnShowDeveloperDetails(int index);


    }
}

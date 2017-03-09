package com.crevator.lagosdev.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.crevator.lagosdev.Model.Developer;

/**
 * Created by Adetuyi Tolu on 3/4/2017.
 */


public class DevProfileContract {
    public interface View extends BaseView<Presenter> {
        void showDevDetails(Developer developer);

        void shareDevDetails(Developer developer);

    }

    public interface Presenter extends BasePresenter<View> {
        void onLoad(Intent intent);

        void OnShareDevDetails();

        void displayImageWith(ImageView imageView);

    }
}

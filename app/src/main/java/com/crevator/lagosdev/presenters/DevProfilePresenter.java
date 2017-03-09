package com.crevator.lagosdev.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.crevator.lagosdev.LagosDevApp;
import com.crevator.lagosdev.Model.Developer;
import com.crevator.lagosdev.R;
import com.crevator.lagosdev.interfaces.DevProfileContract;
import com.crevator.lagosdev.util.Data;
import com.squareup.picasso.Picasso;

/**
 * Created by Slimfit on 3/8/2017.
 */

public class DevProfilePresenter implements DevProfileContract.Presenter {
    DevProfileContract.View mView;
    Developer developer;

    public DevProfilePresenter(DevProfileContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void onLoad(Intent intent) {
        developer = (Developer) intent.getSerializableExtra(Data.PROFILE_DETAILS);
        mView.showDevDetails(developer);
    }

    @Override
    public void OnShareDevDetails() {
        mView.shareDevDetails(developer);
    }

    @Override
    public void displayImageWith(ImageView imageView) {
        Picasso.with(LagosDevApp.getInstance()).load(developer.getImgURL())
                .placeholder(R.color.colorPrimary).into(imageView);
    }

    @Override
    public DevProfileContract.View getView() {
        return mView;
    }

    @Override
    public void onStart() {

    }
}

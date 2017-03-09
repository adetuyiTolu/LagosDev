package com.crevator.lagosdev.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crevator.lagosdev.Model.Developer;
import com.crevator.lagosdev.R;
import com.crevator.lagosdev.interfaces.BaseView;
import com.crevator.lagosdev.interfaces.DevProfileContract;
import com.crevator.lagosdev.presenters.DevProfilePresenter;
import com.crevator.lagosdev.util.Data;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements DevProfileContract.View {
    private DevProfileContract.Presenter mPresenter;
    private FloatingActionButton mShareFloatActionButton;
    private TextView devDetailsView;
    private ImageView devImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        //initialize Profle Presenter
        new DevProfilePresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onLoad(getIntent());
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.toolbar_layout);
        devDetailsView = (TextView) findViewById(R.id.profile_url);
        devImageView = (ImageView) collapsingToolbarLayout.findViewById(R.id.dev_image);
        mShareFloatActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mShareFloatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.OnShareDevDetails();
            }
        });
    }

    @Override
    public void showDevDetails(Developer developer) {
        setTitle(developer.getUserName());
        devDetailsView.setText(developer.getProfileURL());
        mPresenter.displayImageWith(devImageView);
    }

    @Override
    public void shareDevDetails(Developer developer) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Check out this awesome developer @" + developer.getUserName() + " \n" +
                developer.getProfileURL();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Lagos Developers");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(DevProfileContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}

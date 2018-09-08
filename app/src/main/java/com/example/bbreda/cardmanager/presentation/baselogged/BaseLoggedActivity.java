package com.example.bbreda.cardmanager.presentation.baselogged;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;


import com.example.bbreda.cardmanager.R;
import com.example.bbreda.cardmanager.data.model.User;
import com.example.bbreda.cardmanager.data.repository.local.login.LoginLocalRepository;
import com.example.bbreda.cardmanager.presentation.about.AboutActivity;
import com.example.bbreda.cardmanager.presentation.baselogged.BaseLoggedContract.Presenter;
import com.example.bbreda.cardmanager.presentation.carddetails.CardDetailsActivity;
import com.example.bbreda.cardmanager.presentation.mycards.MyCardsActivity;
import com.example.bbreda.cardmanager.presentation.schedulepayment.SchedulePaymentActivity;

public abstract class BaseLoggedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseLoggedContract.View {

    private TextView mNameFromSharedPreferences;

    private BaseLoggedContract.Presenter mPresenter;

    private ContentFrameLayout mViewStub;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_drawer_menu);

        mViewStub = findViewById(R.id.view_stub);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, 0, 0);
        drawer.addDrawerListener(toggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNameFromSharedPreferences = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView_name_sharedPreferences);

        mPresenter = new BaseLoggedPresenter(this);

    }

    public void goToCardDetails() {
        startActivity(new Intent(this, CardDetailsActivity.class));
        finish();

    }

    public void goToMyCards() {
        startActivity(new Intent(this, MyCardsActivity.class));
        finish();

    }

    public void goToSchedulePayment() {
        startActivity(new Intent(this, SchedulePaymentActivity.class));
        finish();

    }

    public void goToAbout() {
        startActivity(new Intent(this, AboutActivity.class));
        finish();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_cartoes) {
            goToMyCards();
        } else if (id == R.id.nav_extrato) {
            goToCardDetails();
        } else if (id == R.id.nav_agendar) {
            goToSchedulePayment();
        } else if (id == R.id.nav_sobre) {
            goToAbout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void setContentView(View view) {
        if (mViewStub != null) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mViewStub.addView(view, lp);

        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if(mViewStub != null){
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            View stubView = inflater.inflate(layoutResID, mViewStub, false);
            mViewStub.addView(stubView, lp);
        }
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        if (mViewStub != null) {
            mViewStub.addView(view, params);
        }
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void showDataUser(User user) {

        mNameFromSharedPreferences.setText(user.getName());

    }

    @Override
    public void setPresenter(Presenter presenter) {

        mPresenter = presenter;

    }

    @Override
    public Context getViewContext() {
        return this;
    }
}

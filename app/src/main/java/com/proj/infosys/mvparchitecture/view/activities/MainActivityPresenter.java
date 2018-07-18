package com.proj.infosys.mvparchitecture.view.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.proj.infosys.mvparchitecture.R;
import com.proj.infosys.mvparchitecture.view.fragments.MainFragment;
import com.proj.infosys.mvparchitecture.view.fragments.MainFragmentPresenter;

public class MainActivityPresenter{

    MainActivity mMainActivity;

    public MainActivityPresenter(MainActivity mainActivity){
        this.mMainActivity = mainActivity;
    }

    public void init(){
        setActionBarTitle("");
    }

    public void setActionBarTitle(String title) {
        mMainActivity.getSupportActionBar().setTitle(title);
    }

}

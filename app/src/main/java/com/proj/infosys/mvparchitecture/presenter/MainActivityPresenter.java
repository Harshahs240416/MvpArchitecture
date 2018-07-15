package com.proj.infosys.mvparchitecture.presenter;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.proj.infosys.mvparchitecture.R;
import com.proj.infosys.mvparchitecture.view.fragments.MainFragment;

public class MainActivityPresenter extends AppCompatActivity{

//    @Override
//    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        View v = super.onCreateView(parent, name, context, attrs);
//        return v;
//    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}

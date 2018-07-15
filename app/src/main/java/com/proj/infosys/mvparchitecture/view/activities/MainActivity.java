package com.proj.infosys.mvparchitecture.view.activities;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.proj.infosys.mvparchitecture.R;
import com.proj.infosys.mvparchitecture.presenter.MainActivityPresenter;
import com.proj.infosys.mvparchitecture.view.fragments.MainFragment;

public class MainActivity extends MainActivityPresenter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

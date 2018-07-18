package com.proj.infosys.mvparchitecture.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.proj.infosys.mvparchitecture.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(this);
//        mainActivityPresenter.init();
    }
}

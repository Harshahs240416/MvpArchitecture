package com.proj.infosys.mvparchitecture.view.fragments;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.proj.infosys.mvparchitecture.model.Row;
import com.proj.infosys.mvparchitecture.presenter.MainActivityPresenter;
import com.proj.infosys.mvparchitecture.view.activities.MainActivity;
import com.proj.infosys.mvparchitecture.view.adapters.ListAdapter;

import java.util.List;

public class MainFragmentView {

    MainActivity mMainActivity;
    ProgressDialog mProgressBar;

    public MainFragmentView(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }

    public void setActionBarTitleForActivity(String actionBarTitleForActivity) {
        mMainActivity.setActionBarTitle(actionBarTitleForActivity);
    }

    public void showToast(String toastMessage) {
        Toast.makeText(mMainActivity, toastMessage, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(String dialogtext) {
        mProgressBar = new ProgressDialog(mMainActivity);
        mProgressBar.setCancelable(true);
        mProgressBar.setMessage(dialogtext);
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.setIndeterminate(true);
        mProgressBar.show();
    }

    public void dismissProgressDialog(){
        if(mProgressBar.isShowing()) {
            mProgressBar.dismiss();
        }
    }

    public void setAdapter(RecyclerView recyclerView, String headerTitle, List<Row> rowLists) {
        ListAdapter listAdapter = new ListAdapter(headerTitle, rowLists);
        recyclerView.setAdapter(listAdapter);
    }
}

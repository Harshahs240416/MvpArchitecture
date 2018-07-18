package com.proj.infosys.mvparchitecture.view.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.proj.infosys.mvparchitecture.R;
import com.proj.infosys.mvparchitecture.model.Model;
import com.proj.infosys.mvparchitecture.model.Row;
import com.proj.infosys.mvparchitecture.view.activities.MainActivity;
import com.proj.infosys.mvparchitecture.view.adapters.ListAdapter;

import java.util.List;

public class MainFragmentView {

    View mView;
    ProgressDialog mProgressBar;
    MainActivity mContext;

    public MainFragmentView(View view, MainActivity context) {
        this.mView = view;
        this.mContext = context;
    }

    public void setActionBarTitleForActivity(String actionBarTitleForActivity) {
        ((MainActivity)mContext).setTitle(actionBarTitleForActivity);
    }

    public void showToast(String toastMessage) {
        Toast.makeText(mContext, toastMessage, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(String dialogtext) {
        mProgressBar = new ProgressDialog(mContext);
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

    public void setAdapter(Model model) {
        RecyclerView recyclerView = mView.findViewById(R.id.list_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        ListAdapter listAdapter = new ListAdapter(model.getRows());
        recyclerView.setAdapter(listAdapter);
    }
}

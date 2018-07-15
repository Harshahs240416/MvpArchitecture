package com.proj.infosys.mvparchitecture.view.fragments;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.proj.infosys.mvparchitecture.R;
import com.proj.infosys.mvparchitecture.model.Row;
import com.proj.infosys.mvparchitecture.presenter.MainActivityPresenter;
import com.proj.infosys.mvparchitecture.view.activities.MainActivity;
import com.proj.infosys.mvparchitecture.view.adapters.ListAdapter;

import java.util.List;

public class MainFragmentView {

    View mView;
    ProgressDialog mProgressBar;

    public MainFragmentView(View view) {
        this.mView = view;
    }

//    public void setActionBarTitleForActivity(String actionBarTitleForActivity) {
//        mView.setActionBarTitle(actionBarTitleForActivity);
//    }

    public void showToast(String toastMessage) {
        Toast.makeText(mView.getContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(String dialogtext) {
        mProgressBar = new ProgressDialog(mView.getContext().getApplicationContext());
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

    public void setAdapter(String headerTitle, List<Row> rowLists) {
        ListAdapter listAdapter = new ListAdapter(headerTitle, rowLists);
        RecyclerView recyclerView = mView.findViewById(R.id.list_view);
        recyclerView.setAdapter(listAdapter);
    }
}

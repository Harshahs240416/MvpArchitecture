package com.proj.infosys.mvparchitecture.view.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proj.infosys.mvparchitecture.R;
import com.proj.infosys.mvparchitecture.model.Row;
import com.proj.infosys.mvparchitecture.view.activities.MainActivity;
import com.proj.infosys.mvparchitecture.view.interactor.WebServiceImpl;
import com.proj.infosys.mvparchitecture.view.interactor.WebServiceInterface;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainFragmentPresenter extends Fragment {

    List<Row> mRowLists;
    String mActionBarTitle;
    RecyclerView mRecyclerView;
    MainFragmentView mMainFragmentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mMainFragmentView = new MainFragmentView((MainActivity) getActivity());
        mMainFragmentView.showProgressDialog("Downloading ...");
        mMainFragmentView.showToast("inside oncreate");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isNetworkAvailable()) {
            getResponseData();
        }
        mMainFragmentView.showToast("Inside onViewCreated");
        if (!isNetworkAvailable()) {
            if (retrieveDataFromSharedPrefs(getString(R.string.shared_Pref)) != null) {
                mMainFragmentView.dismissProgressDialog();
                mMainFragmentView.setAdapter(mRecyclerView, mActionBarTitle, mRowLists);
            }else{
                mMainFragmentView.showToast("No Network, No data to display");
            }
        }
    }

    /*public void checkAdapterData(RecyclerView recyclerView,String actionBarTitle,List<Row> rowList){
        if(actionBarTitle != null || rowList != null){
            mMainFragmentView.setAdapter(mRecyclerView, mActionBarTitle, mRowLists);
        }else{
            checkAdapterData(mRecyclerView, mActionBarTitle, mRowLists);
        }
    }*/

    public void getResponseData(){
        WebServiceImpl webService = new WebServiceImpl();
        webService.getDataFromWebService(getString(R.string.base_url), new WebServiceInterface() {
            @Override
            public void getDataFromWebService(List<Row> rowData, String headerTitle) {
                mActionBarTitle = headerTitle;
                mRowLists = rowData;
                saveDataToSharedPrefs(getString(R.string.shared_Pref), rowData);
                mMainFragmentView.dismissProgressDialog();
                mMainFragmentView.setAdapter(mRecyclerView, mActionBarTitle, mRowLists);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "Fragment onResume", Toast.LENGTH_SHORT).show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(getActivity().getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void saveDataToSharedPrefs(String sharedPrefName, List<Row> responseData) {
        SharedPreferences sp = getActivity().getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(responseData);
        sp.edit().putString("ModelObject", json).commit();
    }

    public ArrayList<Row> retrieveDataFromSharedPrefs(String sharedPrefName) {
        SharedPreferences sp = getActivity().getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("ModelObject", "");
        Type type = new TypeToken<List<Row>>() {
        }.getType();
        return gson.fromJson(json, type);
    }



}

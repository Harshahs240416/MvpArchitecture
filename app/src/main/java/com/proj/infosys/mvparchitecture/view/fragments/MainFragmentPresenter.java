package com.proj.infosys.mvparchitecture.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proj.infosys.mvparchitecture.R;
import com.proj.infosys.mvparchitecture.model.Model;
import com.proj.infosys.mvparchitecture.interactor.WebServiceImpl;
import com.proj.infosys.mvparchitecture.interactor.WebServiceInterface;
import java.lang.reflect.Type;

public class MainFragmentPresenter {

    MainFragmentView mMainFragmentView;
    WebServiceImpl mWebService;
    Model mModel;
    Context mContext;


    public MainFragmentPresenter(MainFragmentView mainFragmentView, WebServiceImpl webServiceObj, Context context) {
        this.mMainFragmentView = mainFragmentView;
        this.mWebService = webServiceObj;
        this.mContext = context;
    }

    public void init() {
        mMainFragmentView.showProgressDialog("Downloading...");
        if (isNetworkAvailable()) {
            getResponseData();
        } else {
          Model model = retrieveDataFromSharedPrefs(mContext.getString(R.string.shared_Pref));
            if (model != null) {
                mMainFragmentView.dismissProgressDialog();
                mMainFragmentView.setActionBarTitleForActivity(model.getTitle());
                mMainFragmentView.setAdapter(model);
            } else {
                mMainFragmentView.dismissProgressDialog();
                mMainFragmentView.showToast("No Network, No data to display");
            }
        }
    }

    public void getResponseData() {
        WebServiceImpl webService = new WebServiceImpl();
        webService.getDataFromWebService("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/", new WebServiceInterface() {
            @Override
            public void getDataFromWebService(Model modelData) {
                mModel = modelData;
                saveDataToSharedPrefs(mContext.getString(R.string.shared_Pref), mModel);
                mMainFragmentView.dismissProgressDialog();
                mMainFragmentView.setActionBarTitleForActivity(modelData.getTitle());
                mMainFragmentView.setAdapter(mModel);
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void saveDataToSharedPrefs(String sharedPrefName,Model responseData) {
        SharedPreferences sp = mContext.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(responseData);
        sp.edit().putString("ModelObject", json).commit();
    }

    public Model retrieveDataFromSharedPrefs(String sharedPrefName) {
        SharedPreferences sp = mContext.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("ModelObject", "");
        Type type = new TypeToken<Model>() {
        }.getType();
        return gson.fromJson(json, type);
    }


}

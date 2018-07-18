package com.proj.infosys.mvparchitecture.interactor;

import com.proj.infosys.mvparchitecture.network.ClientInterface;
import com.proj.infosys.mvparchitecture.network.NetworkClient;
import com.proj.infosys.mvparchitecture.model.Model;
import com.proj.infosys.mvparchitecture.model.Row;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceImpl {

    Model mModel;
    WebServiceInterface mWebServiceInterface;

    public void getDataFromWebService(String baseUrl, WebServiceInterface webServiceInterface) {
        mWebServiceInterface = webServiceInterface;
        ClientInterface clientInterface = NetworkClient.getClient(baseUrl).create(ClientInterface.class);
        retrofit2.Call<Model> call = clientInterface.getRowData();
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(retrofit2.Call<Model> call, Response<Model> response) {
                mModel =response.body();
                List<Row> rowLists = response.body().getRows();
                String title = response.body().getTitle();
                mWebServiceInterface.getDataFromWebService(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<Model> call, Throwable t) {
                mModel = null;
            }
        });
    }
}

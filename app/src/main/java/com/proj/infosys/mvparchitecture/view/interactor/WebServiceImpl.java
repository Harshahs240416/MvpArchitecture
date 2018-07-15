package com.proj.infosys.mvparchitecture.view.interactor;
import com.proj.infosys.mvparchitecture.network.ClientInterface;
import com.proj.infosys.mvparchitecture.network.NetworkClient;
import com.proj.infosys.mvparchitecture.model.Model;
import com.proj.infosys.mvparchitecture.model.Row;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceImpl{

    List<Row> rowLists = new ArrayList<Row>();
    String title;
    WebServiceInterface mWebServiceInterface;

    public void getDataFromWebService(String baseUrl, WebServiceInterface webServiceInterface) {
        mWebServiceInterface = webServiceInterface;
        ClientInterface clientInterface = NetworkClient.getClient(baseUrl).create(ClientInterface.class);
        retrofit2.Call<Model> call = clientInterface.getRowData();
        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(retrofit2.Call<Model> call, Response<Model> response) {
                rowLists = response.body().getRows();
                title = response.body().getTitle();
                mWebServiceInterface.getDataFromWebService(rowLists, title);
            }

            @Override
            public void onFailure(retrofit2.Call<Model> call, Throwable t) {
                rowLists = null;
            }
        });
    }
}

package com.proj.infosys.mvparchitecture.network;

import com.proj.infosys.mvparchitecture.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientInterface {

    @GET("facts")
    Call<Model> getRowData();

}

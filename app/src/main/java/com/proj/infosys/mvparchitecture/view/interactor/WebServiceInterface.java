package com.proj.infosys.mvparchitecture.view.interactor;

import com.proj.infosys.mvparchitecture.model.Row;
import com.proj.infosys.mvparchitecture.view.fragments.MainFragmentPresenter;

import java.util.List;

public interface WebServiceInterface {

    public void getDataFromWebService(List<Row> rowData, String title);

}

package com.proj.infosys.mvparchitecture.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.proj.infosys.mvparchitecture.R;
import com.proj.infosys.mvparchitecture.model.Row;
import com.proj.infosys.mvparchitecture.view.activities.MainActivity;
import com.proj.infosys.mvparchitecture.view.interactor.WebServiceImpl;

import java.util.List;

public class MainFragment extends Fragment {

    List<Row> mRowLists;
    String mActionBarTitle;
    RecyclerView mRecyclerView;
    MainFragmentView mMainFragmentView;
    WebServiceImpl mWebServiceImpl;
    MainFragmentPresenter mMainFragmentPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        Toast.makeText(getActivity(), "Fragment OncreateView", Toast.LENGTH_SHORT).show();
        mMainFragmentView = new MainFragmentView(view);
        mWebServiceImpl = new WebServiceImpl();
        mMainFragmentPresenter = new MainFragmentPresenter(mMainFragmentView, mWebServiceImpl, view);
        mMainFragmentPresenter.init();
        return view;
    }

}

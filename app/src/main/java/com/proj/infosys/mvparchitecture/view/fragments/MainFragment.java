package com.proj.infosys.mvparchitecture.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proj.infosys.mvparchitecture.R;
import com.proj.infosys.mvparchitecture.interactor.WebServiceImpl;
import com.proj.infosys.mvparchitecture.view.activities.MainActivity;

import java.util.List;

public class MainFragment extends Fragment {

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
        mMainFragmentView = new MainFragmentView(view, (MainActivity) getActivity());
        mWebServiceImpl = new WebServiceImpl();
        mMainFragmentPresenter = new MainFragmentPresenter(mMainFragmentView, mWebServiceImpl, getActivity().getApplicationContext());
        mMainFragmentPresenter.init();
        return view;
    }

}

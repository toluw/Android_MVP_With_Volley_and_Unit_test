package com.up.mvp.Presenter;

import com.up.mvp.Contract.MainContract;
import com.up.mvp.Contract.SleepContract;
import com.up.mvp.Modell.Model;

public class SleepPresenter implements SleepContract.Presenter{
    SleepContract.View view;
    Model model;

    public SleepPresenter(SleepContract.View view, Model model){
        this.view = view;
        this.model = model;
    }

}

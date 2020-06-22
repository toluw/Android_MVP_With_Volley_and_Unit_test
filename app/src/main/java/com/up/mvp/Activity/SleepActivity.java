package com.up.mvp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.up.mvp.Contract.SleepContract;
import com.up.mvp.Modell.Model;
import com.up.mvp.Presenter.SleepPresenter;
import com.up.mvp.R;

public class SleepActivity extends AppCompatActivity implements SleepContract.View {

    SleepContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        initialize();

    }

    private void initialize(){
        presenter = new SleepPresenter(this, new Model(this));
    }

}

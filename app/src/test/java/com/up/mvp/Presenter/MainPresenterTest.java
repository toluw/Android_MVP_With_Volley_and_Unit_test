package com.up.mvp.Presenter;

import com.up.mvp.Contract.MainContract;
import com.up.mvp.Modell.Model;

import org.hamcrest.CoreMatchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MainPresenterTest {

    MainContract.View viewMock = mock(MainContract.View.class);   //Mock view class
    Model modelMock = mock(Model.class);   //mock model class



    MainPresenter SUT;



    @Before
    public void setUp() throws Exception {
       SUT = new MainPresenter(viewMock,modelMock);
    }

    @Test
    public void loadJob() {
        SUT.loadJob("http://mypost/post.php");
        verify(modelMock,timeout(1)).get("http://mypost/post.php",SUT);
        verify(viewMock).showDialog();
    }




}
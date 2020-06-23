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
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MainPresenterTest {

    MainContract.View viewMock = mock(MainContract.View.class);   //Mock view class
    Model modelMock = mock(Model.class);   //mock model class

    Map params  = mock(HashMap.class);




    MainPresenter SUT;

    String url = "http://mypost/post.php";

    String errorMsg = "An error occured";



    @Before
    public void setUp() throws Exception {
       SUT = new MainPresenter(viewMock,modelMock);
    }

    @Test
    public void loadJob() {
        SUT.loadJob(url);
        verify(modelMock,timeout(1)).get(url,SUT);
        verify(viewMock).showDialog();
    }

    @Test
    public void loadProf() {
        SUT.loadProf("http://mypost/post.php",params);
        verify(modelMock,timeout(1)).post("http://mypost/post.php",params,SUT);
        verify(viewMock).showDialog();
    }


    @Test
    public void getResponse() {
        JSONObject object = mock(JSONObject.class);
       SUT.getResponse(object,url);
       verify(viewMock).closeDialog();
        verify(viewMock).showResponse(object,url);
    }


    @Test
    public void getError() {
        SUT.getError(errorMsg,url);
        verify(viewMock).closeDialog();
        verify(viewMock).showError(errorMsg,url);

    }
}
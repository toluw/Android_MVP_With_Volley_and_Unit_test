package com.up.mvp.Presenter;

import com.up.mvp.Contract.MainContract;
import com.up.mvp.Modell.Model;

import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MainPresenterTest {

    MainContract.View viewMock = mock(MainContract.View.class);
    Model modelMock = mock(Model.class);

    MainPresenter SUT;

    @Before
    public void setUp() throws Exception {
       SUT = new MainPresenter(viewMock,modelMock);
    }

    @Test
    public void loadJob() {
        SUT.loadJob("http://35.205.69.78/project/prof_store.php");
        verify(modelMock,timeout(1)).get("http://35.205.69.78/project/prof_store.php",SUT);
        verify(viewMock).showDialog();
    }

    @Test
    public void getJobArray_returnExpectedValue() {
        try {
            JSONObject object = mock(JSONObject.class);
            object.put("number", new Integer(100));
            object.put("0", "Clement");
            object.put("k0", new Integer(38));
            object.put("1", "John");
            object.put("k1", "7");
            object.put("2", "Tolu");
            object.put("k2", "9");

            String[] bd = new String[3];
            bd[0] = "1. Tolu";
            bd[1] = "2. John";
            bd[2] = "3. Clement";

           String[]kk = SUT.getJobArray(object);
            assertThat(kk, is(bd));


        }
        catch (JSONException e){

        }


    }
}
package com.up.mvp.Presenter;

import com.up.mvp.Contract.MainContract;
import com.up.mvp.Modell.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    Model model;
    String[] m;
    ArrayList<String> listItems;

    public MainPresenter(MainContract.View view, Model model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadProf(String url, Map<String, String> params) {
        view.showDialog();
        model.post(url,params,this);
    }

    @Override
    public void loadJob(String url) {
        view.showDialog();
         model.get(url,this);
    }


    @Override
    public void getResponse(JSONObject jsonObject, String url) {
        view.closeDialog();
        view.showResponse(jsonObject,url);
    }

    @Override
    public void getError(String errMsg, String url) {
       view.closeDialog();
       view.showError(errMsg,url);
    }

    //
    @Override
    public String[] getJobArray(JSONObject jsonObject) {
        try {
            final int v = jsonObject.getInt("number");
             m = new String[v];
            String[] tit = new String[v];
            int[] em = new int[v];

            //x2
            String[] id = new String[v];

            int k = v - 1;
            int i = k;
            int q;
            int o;
            while (i >= 0) {
                q = k - i;
                String y = String.valueOf(i);
                String p = jsonObject.getString(y);
                int d = jsonObject.getInt("k" + y);
                tit[q] = p;

                em[q] = d;
                //x3
                id[q] = String.valueOf(em[q]);
                o = q + 1;
                String wa = String.valueOf(o);
                String lap = wa + ".  " + p;
                m[q] = lap;
                i--;
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return m;
    }


    @Override
    public ArrayList<String> getProfArray(JSONObject jsonObject) {

        try {
            JSONArray  jarray = jsonObject.getJSONArray("game");
            String[] tit = new String[jarray.length()];
            for (int i = 0; i < jarray.length(); i++){
                JSONObject object = jarray.getJSONObject(i);
                tit[i]= object.getString("title");
            }
            listItems = new ArrayList<>(Arrays.asList(tit));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listItems;

    }
}

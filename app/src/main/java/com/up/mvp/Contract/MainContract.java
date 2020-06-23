package com.up.mvp.Contract;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public interface MainContract {

    interface View {
        void showDialog();

        void showResponse(JSONObject jsonObject, String url);


        void showError(String errMsg, String url);

        void closeDialog();
    }

    interface Presenter{
        void loadProf(String url, Map<String, String> params);

        void loadJob(String url);

        void getResponse(JSONObject jsonObject, String url);

        void getError(String errMsg, String url);

        String[] getJobArray(JSONObject jsonObject);

        ArrayList<String>getProfArray(JSONObject jsonObject);

    }
}

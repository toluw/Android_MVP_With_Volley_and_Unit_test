package com.up.mvp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.up.mvp.Contract.MainContract;
import com.up.mvp.Modell.Model;
import com.up.mvp.Presenter.MainPresenter;
import com.up.mvp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    ProgressBar dialog;
    ListView list;

    String posturl = "http://mypost/post.php";  // URL for post request
    String geturl = "http://myget/get.php";  //URL for get request

    MainContract.Presenter presenter;

    Map<String, String> params  = new HashMap<String, String>();

    TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            initialize();


    }

    private void initialize(){
        list = (ListView)findViewById(R.id.list);
        dialog = (ProgressBar)findViewById(R.id.down);
        text = (TextView)findViewById(R.id.text);


        presenter = new MainPresenter(this, new Model(this));
    }

    //Perform Post request to API
    public void prof (View view){

        //Parameters of post request
        params.put("value","200");
        params.put("name","Emma");

        //perform API call
        presenter.loadProf(posturl, params);
    }


    //Perform Get request to API
    public void job(View view){
      presenter.loadJob(geturl);
    }

    //SHow progress dialog
    @Override
    public void showDialog() {
     dialog.setVisibility(View.VISIBLE);
    }

    //Response gotten from volley call
    @Override
    public void showResponse(JSONObject jsonObject, String url) {
    if(url.equals(posturl)){
        //Response from POST call
        try {

            if (jsonObject.has("response")){
                String  num = jsonObject.getString("response");
                Toast a = Toast.makeText(getApplicationContext(), num, Toast.LENGTH_LONG);
                a.setGravity(Gravity.CENTER, 0, 0);
                a.show();
            }
            else {
                text.setText("Post result");
                ArrayList<String> listItems =  presenter.getProfArray(jsonObject); //Gets data to be displayed on list view

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listItems);
                list.setAdapter(adapter);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }else if(url.equals(geturl)){
        //response from get call
        try {

            if (jsonObject.has("response")){
                String  num = jsonObject.getString("response");
                Toast a = Toast.makeText(getApplicationContext(), num, Toast.LENGTH_LONG);
                a.setGravity(Gravity.CENTER, 0, 0);
                a.show();

            }else
            {
                text.setText("Get Calls");
                String []m =  presenter.getJobArray(jsonObject);  //Gets Data to be displayed
                ArrayList<String> listItems = new ArrayList<>(Arrays.asList(m));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listItems);
                list.setAdapter(adapter);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }

    //Error response from Volley call
    @Override
    public void showError(String errMsg, String url) {
          text.setText(errMsg);
    }

    //Close progress dialog
    @Override
    public void closeDialog() {
    dialog.setVisibility(View.GONE);
    }
}

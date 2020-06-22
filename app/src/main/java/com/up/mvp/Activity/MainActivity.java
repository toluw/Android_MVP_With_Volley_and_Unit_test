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
    String profurl = "http://35.205.69.78/project/prof_store.php";
    String joburl = "http://35.205.69.78/project/jothers1.php";
    MainContract.Presenter presenter;

    Map<String, String> params;

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

    //Professonal exams button clicked
    public void prof (View view){
        //Data to be posted
        params  = new HashMap<String, String>();
        params.put("table","book_pnse");

        //profurl is the API url
        presenter.loadProf(profurl, params);
    }


    //Job button clicked
    public void job(View view){
      presenter.loadJob(joburl);
    }

    //Dialog shown
    @Override
    public void showDialog() {
     dialog.setVisibility(View.VISIBLE);
    }

    //Response gotten from volley call
    @Override
    public void showResponse(JSONObject jsonObject, String url) {
    if(url.equals(profurl)){
        //Response for professional exam volley call
        try {

            if (jsonObject.has("response")){
                String  num = jsonObject.getString("response");
                Toast a = Toast.makeText(getApplicationContext(), num, Toast.LENGTH_LONG);
                a.setGravity(Gravity.CENTER, 0, 0);
                a.show();
            }
            else {
                text.setText("Professional Exams");
                JSONArray jarray = jsonObject.getJSONArray("game");


               String[] tit = new String[jarray.length()];

                for (int i = 0; i < jarray.length(); i++){
                    JSONObject object = jarray.getJSONObject(i);
                    tit[i]= object.getString("title");

                }
                ArrayList<String> listItems = new ArrayList<>(Arrays.asList(tit));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listItems);
                list.setAdapter(adapter);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }else if(url.equals(joburl)){
        //Response for Job questions volley call
        try {

            if (jsonObject.has("response")){
                String  num = jsonObject.getString("response");
                Toast a = Toast.makeText(getApplicationContext(), num, Toast.LENGTH_LONG);
                a.setGravity(Gravity.CENTER, 0, 0);
                a.show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }else
            {
                text.setText("Job Questions");
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

    @Override
    public void showError(String errMsg, String url) {
          text.setText(errMsg);
    }

    @Override
    public void closeDialog() {
    dialog.setVisibility(View.GONE);
    }
}

package org.showmeyourcode.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Button getAll;
    Button get30;

    ArrayList<String> allNames;
    ArrayList<String> allNumbers;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getAll = (Button) findViewById(R.id.all);
        get30 = (Button) findViewById(R.id.thirty);


        allNames = new ArrayList<String>();
        allNumbers = new ArrayList<String>();

        getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONProccess1("http://showmeyourcode.org/test.php");

            }
        });

        get30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONProccess2("http://showmeyourcode.org/test2.php?num=30");

            }
        });


    }

    private void JSONProccess1  ( String loginURL ) {

        requestQueue = Volley.newRequestQueue(this);
        // output = (TextView) findViewById(R.id.jsonData);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, loginURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray ja = response.getJSONArray("results");

                            for (int i = 0; i < ja.length(); i++) {

                                JSONObject jsonObject = ja.getJSONObject(i);

                                String name = jsonObject.getString("name");
                                String number = jsonObject.getString("number");

                                allNames.add(name);
                                allNumbers.add(number);
                            }

                            for (int i=0; i< allNumbers.size(); i++ ) {
                                Toast.makeText(MainActivity.this, allNames.get(i) + ", " + allNumbers.get(i), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");

                    }
                }
        );
        requestQueue.add(jor);


    }

    private void JSONProccess2  ( String loginURL ) {

        requestQueue = Volley.newRequestQueue(this);
        // output = (TextView) findViewById(R.id.jsonData);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, loginURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray ja = response.getJSONArray("results");



                            JSONObject jsonObject = ja.getJSONObject(0);

                            String name = jsonObject.getString("name");
                            String number = jsonObject.getString("number");

                            Toast.makeText(MainActivity.this, name + ", " + number , Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");

                    }
                }
        );
        requestQueue.add(jor);


    }
}

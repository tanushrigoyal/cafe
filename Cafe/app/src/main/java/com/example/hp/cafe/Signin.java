package com.example.hp.cafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class Signin extends AppCompatActivity {


    private Button button;
    private static final String TAG = "SigninActivity";
    EditText phone , pass;
    String login_url;
    String success , error,test;

    public class Details{
        String adhar_Id;
        String seller_Id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        button = (Button)findViewById(R.id.buttonsignin);
        pass = (EditText)findViewById(R.id.edtPass);
        phone = (EditText)findViewById(R.id.edtPhone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Phone, Pass;
                Phone = phone.getText().toString();
                Pass = pass.getText().toString();
                login_url = "http://192.168.43.202:8080/login/" + Phone +"/" + Pass;
                JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET, login_url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try
                                {
                                    success = response.getString("success");
                                    error = response.getString("error");
                                    test = response.getString("result");

                                    Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();

//                                    Gson gson = new Gson();
//                                    JsonParser parser = new JsonParser();
//                                    JsonArray array = parser.parse(test).getAsJsonArray();
//                                    Details details = gson.fromJson(array.get(0), Details.class);

                                    if(error.equals("hell"))
                                    {
                                       Intent intent=new Intent(Signin.this,Home.class);
//                                        intent.putExtra("adhar_Id",details.adhar_Id);
//                                        intent.putExtra("seller_Id",details.seller_Id);
                                        startActivity(intent);
//                                        SharedPreferences sharedPreferences = getSharedPreferences("My pref",MODE_PRIVATE);
//                                        String name = sharedPreferences.getString(Config.KEY_ABC_Adar,null);
                                        //.putExtra("movie",aJsonString).putExtra("song",bJsonString)

                                    }
                                    else {

                                        Toast.makeText(Signin.this,"Incorrect phone no. or password!",Toast.LENGTH_SHORT).show();

                                    }
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Signin.this,"something went wrong!!!",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

                MySingleton.getInstance(Signin.this).addToRequestque(JsonObjectRequest);
            }
        });
    }
}


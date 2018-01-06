package com.example.hp.cafe;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    EditText phone_edit, pass_edit, name_edit ;
    String entered_name,entered_pass,entered_phone, signup_url, success, message;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        button=(Button) findViewById(R.id.buttonsignup);
        phone_edit=(EditText)findViewById(R.id.edtPhone);
        pass_edit=(EditText)findViewById(R.id.edtPass);
        name_edit=(EditText)findViewById(R.id.edtName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                entered_phone = phone_edit.getText().toString();
                entered_name = name_edit.getText().toString();
                entered_pass = pass_edit.getText().toString();

                final HashMap<String, String> postParams = new HashMap<String, String>();
                postParams.put("name", entered_name);
                postParams.put("phone", entered_phone);
                postParams.put("pwd", entered_pass);

                signup_url = "http://192.168.43.202:8080/signup/";

                JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.POST, signup_url, new JSONObject(postParams),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    success = response.getString("success");
                                    message = response.getString("msg");
                                    if (success.equals("true")) {

                                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(SignUp.this, "successfully registered!", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SignUp.this, "something went wrong!!!", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }
                };

                MySingleton.getInstance(SignUp.this).addToRequestque(JsonObjectRequest);


            }
        });
    }
}

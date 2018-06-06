package com.example.dell.cabs_pos20.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.utilities.Constants;
import com.example.dell.cabs_pos20.utilities.AppSingleton;
import com.example.dell.cabs_pos20.utilities.Authentications;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordActivity extends AppCompatActivity{

    private EditText pass;
    private EditText cnfmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        pass = findViewById(R.id.pass);
        cnfmPass = findViewById(R.id.passCnfm);
        Button submit = findViewById(R.id.buttonReset);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();
                String strPass = pass.getText().toString().trim();
                String strCnfmPass = cnfmPass.getText().toString().trim();

                if(strPass.equals(strCnfmPass)){
                    if(strCnfmPass.length() >= 6){

                        final Authentications mAuthentications = new Authentications();
                       mAuthentications.setPassword(strCnfmPass);

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_RESET, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    String message = jsonObject.getString("message");

                                    if(code.equals("success")){

                                        LoginActivity.startIntent(ResetPasswordActivity.this, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        Toast.makeText(ResetPasswordActivity.this, message, Toast.LENGTH_LONG).show();
                                    }
                                    else if (code.equals("failed")){
                                        Toast.makeText(ResetPasswordActivity.this, message, Toast.LENGTH_LONG).show();

                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(@NonNull VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                                error.printStackTrace();
                            }
                        }){
                            @NonNull
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                Intent intent = getIntent();
                                String strIntent = intent.getStringExtra("Email");
                                params.put("Email",strIntent);
                                params.put("Password", mAuthentications.getPassword() );

                                return params;
                            }
                        };

                        AppSingleton.getInstance(ResetPasswordActivity.this).addToRequestQueue(stringRequest);
                    }
                    else{

                        Toast.makeText(ResetPasswordActivity.this, "Password too short minimum 6 characters", Toast.LENGTH_LONG).show();

                    }
                }
                else{
                    Toast.makeText(ResetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    private void validate() {

        String strPassword = pass.getText().toString().trim();
        String strCnfmPassword = cnfmPass.getText().toString().trim();

        if (strPassword.isEmpty()) {
            pass.setError("Enter your Password");

        } else {
            pass.setError(null);

        }

        if (strCnfmPassword.isEmpty()) {
            cnfmPass.setError("Enter your Password");
        } else {
            cnfmPass.setError(null);

        }

    }
}


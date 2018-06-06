package com.example.dell.cabs_pos20.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText email;
    private EditText natID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.Email);
        natID = findViewById(R.id.NatID);
        Button submit = findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strEmail = email.getText().toString().trim();
                String strNatID = natID.getText().toString().trim();
                final Authentications authentications = new Authentications();


                if (TextUtils.isEmpty(strEmail))
                    email.setError("Enter your Email Address");
                else  if (TextUtils.isEmpty(strNatID))
                    natID.setError("Enter your National ID");
                else {
                    if (strEmail.contains("@")) {
                        authentications.setUsername(strEmail);
                        authentications.setNationalID(strNatID);


                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_RECOVERY, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    String message = jsonObject.getString("message");

                                    if (code.equals("success")) {

                                        Intent intent = new Intent(getApplication(), ResetPasswordActivity.class);
                                        intent.putExtra("Email", authentications.getUsername());
                                        startActivity(intent);

                                    } else if (code.equals("failed")) {
                                        Toast.makeText(ForgotPasswordActivity.this, message, Toast.LENGTH_LONG).show();
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(@NonNull VolleyError error) {

                                Toast.makeText(ForgotPasswordActivity.this, "Error", Toast.LENGTH_LONG).show();
                                error.printStackTrace();

                            }
                        }) {
                            @NonNull
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("NatID", authentications.getNationalID());
                                params.put("Email", authentications.getUsername());
                                return params;

                            }
                        };

                        AppSingleton.getInstance(ForgotPasswordActivity.this).addToRequestQueue(stringRequest);

                    } else {
                        Toast.makeText(ForgotPasswordActivity.this, "Your email format is incorrect", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }





}

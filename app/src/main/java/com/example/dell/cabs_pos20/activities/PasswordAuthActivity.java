package com.example.dell.cabs_pos20.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class PasswordAuthActivity  extends AppCompatActivity {


    private static final String TAG = PasswordAuthActivity.class.getSimpleName();
    @Nullable
    @BindView(R.id.pass)
    EditText pass;
    @Nullable
    @BindView(R.id.passCnfm)
    EditText cnfmPass;
    @Nullable
    @BindView(R.id.buttonReset)
    Button submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        submit.setText(R.string.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPassword = pass.getText().toString().trim();
                String strCnfmPassword = cnfmPass.getText().toString().trim();
                if (strPassword.length() >= 6) {
                    if (strPassword.equals(strCnfmPassword)) {

                        final Authentications mAuthentications = new Authentications();
                        mAuthentications.setPassword(strCnfmPassword);
                        StringRequest stringRequestReg = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonArrayReg = new JSONArray(response);
                                    Log.v(TAG, response);
                                    JSONObject jsonObjectReg = jsonArrayReg.getJSONObject(0);
                                    String code = jsonObjectReg.getString("code");
                                    String message = jsonObjectReg.getString("message");

                                    if (code.equals("Registration Success")) {
                                        Toast.makeText(PasswordAuthActivity.this, message, Toast.LENGTH_LONG).show();
                                        DrawerActivity.startIntent(PasswordAuthActivity.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                    } else if (code.equals("Registration failed")) {

                                        // write error code
                                        Toast.makeText(PasswordAuthActivity.this, message, Toast.LENGTH_LONG).show();
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(@NonNull VolleyError error) {

                                Toast.makeText(PasswordAuthActivity.this, "Error", Toast.LENGTH_LONG).show();
                                error.printStackTrace();
                            }
                        }) {
                            @NonNull
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                Intent intent = getIntent();
                                String[] array = intent.getStringArrayExtra(Constants.REGISTRATION_DATA);

                                params.put("FirstName",array[2]);
                                params.put("LastName",  array[3]);
                                params.put("NationalID", array[1]);
                                params.put("Password",  mAuthentications.getPassword());
                                 params.put("Email",  array[0]);

                                //params.get();


                                return params;
                            }

                        };

                        AppSingleton.getInstance(PasswordAuthActivity.this).addToRequestQueue(stringRequestReg);
                    } else {

                        Toast.makeText(PasswordAuthActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();

                    }
                } else {

                    Toast.makeText(PasswordAuthActivity.this, "Password too short minimum 6 characters", Toast.LENGTH_LONG).show();
                }
                    }
        });


    }

}
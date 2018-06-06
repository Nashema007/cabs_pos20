package com.example.dell.cabs_pos20.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.utilities.Constants;
import com.example.dell.cabs_pos20.utilities.AppSingleton;
import com.example.dell.cabs_pos20.utilities.Authentications;
import com.example.dell.cabs_pos20.utilities.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {



    public static void startIntent(@NonNull Context context) {

        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);

    }

    public static void startIntent(@NonNull Context context, int flags) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

   // private static final String TAG = LoginActivity.class.getSimpleName();


   // private LocalDateTime loginTime;
    @NonNull
    private Authentications authentications = new Authentications();
    @NonNull
    private SharedPref sharedPref = new SharedPref(this);


    @Nullable
    @BindView(R.id.LoginBtn) Button loginButton;
    @Nullable
    @BindView(R.id.LoginPassword) EditText password;
    @Nullable
    @BindView(R.id.LoginID) EditText username;
    @Nullable
    @BindView(R.id.ForgotPassTxt) TextView forgotPass;
    @Nullable
    @BindView(R.id.Register) TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String sharedUsername = sharedPref.getString("username", "");
        String sharedPassword = sharedPref.getString("password", "");

        //if user once logged in login info
        //is queried from shared preferences
        // and checked for validity
        if (!(sharedPassword.equals("") && sharedUsername.equals(""))) {
            login();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strUsername = username.getText().toString().trim();
                String strPassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(strUsername))
                    username.setError("Enter your email address");
                else if (!(strUsername.contains("@")))
                    username.setError("Username must be a valid email address");
                else if (TextUtils.isEmpty(strPassword)){
                    password.setError("Enter your password");
                }
                else {
                    // Volley code

                    authentications.setUsername(strUsername);
                    authentications.setPassword(strPassword);
                    StringRequest myStringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                // gets response from php file for success or failure
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");

                                if (code.equals("Login success")) {


                                    sharedPref.saveString("username", authentications.getUsername());
                                    sharedPref.saveString("password", authentications.getPassword());

                                    DrawerActivity.startIntent(LoginActivity.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);



                                } else if (code.equals("Login failed")) {

                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if (error instanceof TimeoutError) {
                                Toast.makeText(LoginActivity.this, "Login attempt has timed out. Please try again.",
                                        Toast.LENGTH_LONG).show();

                            } else if (error instanceof NetworkError) {
                                Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                            } else if (error instanceof ServerError) {
                                Toast.makeText(LoginActivity.this, "Server is down", Toast.LENGTH_LONG).show();

                            }
                            error.printStackTrace();

                        }


                    }) {
                        @NonNull
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            // stores the login details using key pair system
                            Map<String, String> params = new HashMap<>();
                            params.put("Username", authentications.getUsername());
                            params.put("Password", authentications.getPassword());

                            return params;

                        }
                    };

                    AppSingleton.getInstance(LoginActivity.this).addToRequestQueue(myStringRequest);

                }

                }


        });




    }



    private void login() {

            StringRequest myStringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        // gets response from php file for success or failure
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String code = jsonObject.getString("code");
                        if (code.equals("Login success")) {

                            DrawerActivity.startIntent(LoginActivity.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


                        } else if (code.equals("Login failed")) {
                            Toast.makeText(LoginActivity.this, "Your password has been recently changed please input the new one"
                                    , Toast.LENGTH_LONG).show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if (error instanceof TimeoutError) {
                        Toast.makeText(LoginActivity.this, "Login attempt has timed out. Please try again.",
                                Toast.LENGTH_LONG).show();

                    } else if (error instanceof NetworkError) {
                        Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                    } else if (error instanceof ServerError) {
                        Toast.makeText(LoginActivity.this, "Server is down", Toast.LENGTH_LONG).show();

                    }
                    error.printStackTrace();


                }


            }) {
                @NonNull
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    String sharedUsername = sharedPref.getString("username", "");
                    String sharedPassword = sharedPref.getString("password", "");
                    params.put("Username", sharedUsername);
                    params.put("Password", sharedPassword);

                    return params;

                }
            };

            AppSingleton.getInstance(LoginActivity.this).addToRequestQueue(myStringRequest);


        }
}

package com.example.dell.cabs_pos20.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.utilities.Authentications;
import com.example.dell.cabs_pos20.utilities.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {



    @Nullable
    @BindView(R.id.FirstName)
    EditText firstName;
    @Nullable
    @BindView(R.id.LastName)
    EditText lastName;
    @Nullable
    @BindView(R.id.RegEmail)
    EditText regEmail;
    @Nullable
    @BindView(R.id.RegNatId)
    EditText regNatId;
    @Nullable
    @BindView(R.id.Regbutton)
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Authentications authentications = new Authentications();

                String strFname = firstName.getText().toString().trim();
                String strLname = lastName.getText().toString().trim();
                String strEmail = regEmail.getText().toString().trim();
                String strNatID = regNatId.getText().toString().trim();
                if (TextUtils.isEmpty(strFname))
                    firstName.setError("Enter your First name");
                else if (TextUtils.isEmpty(strLname))
                    lastName.setError("Enter your Surname");
                else if (TextUtils.isEmpty(strNatID))
                    regNatId.setError("Enter your National ID");
                else if (TextUtils.isEmpty(strEmail))
                    regEmail.setError("Enter your Email Address");
                else {
                    authentications.setFirstName(firstName.getText().toString().trim());
                    authentications.setUsername(regEmail.getText().toString().trim());
                    authentications.setLastName(lastName.getText().toString().trim());
                    authentications.setNationalID(regNatId.getText().toString().trim());

                    String[] array = {authentications.getUsername(), authentications.getNationalID(), authentications.getFirstName(), authentications.getLastName()};

                    Intent intent = new Intent(RegisterActivity.this, PasswordAuthActivity.class);
                    intent.putExtra(Constants.REGISTRATION_DATA, array);
                    startActivity(intent);
                }

            }
        });


    }


}

package com.example.dell.cabs_pos20.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.adapters.ViewPagerAdapter;
import com.example.dell.cabs_pos20.fragments.VeriFone520_Frag;
import com.example.dell.cabs_pos20.fragments.VeriFone675_Frag;
import com.example.dell.cabs_pos20.fragments.VeriFone_e265_Frag;

public class TutorialsActivity extends AppCompatActivity{

private TabLayout tabLayout;
private ViewPager viewPager;
private ViewPagerAdapter viewPagerAdapter;
@Nullable
private ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);


        tabLayout =findViewById(R.id.VeriFoneTab);
        viewPager = findViewById(R.id.ViewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());




        viewPagerAdapter.addFragment(new VeriFone520_Frag(), "VeriFone 520");
        viewPagerAdapter.addFragment(new VeriFone675_Frag(), "VeriFone 675");
        viewPagerAdapter.addFragment(new VeriFone_e265_Frag(), "VeriFone e265");



        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0F);
        }


    }


}

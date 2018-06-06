package com.example.dell.cabs_pos20.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.adapters.HomeAdapter;
import com.example.dell.cabs_pos20.utilities.Home;
import com.example.dell.cabs_pos20.utilities.SharedPref;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static void startIntent(@NonNull Context context) {

        Intent intent = new Intent(context, DrawerActivity.class);
        context.startActivity(intent);

    }

    public static void startIntent(@NonNull Context context, int flags) {
        Intent intent = new Intent(context, DrawerActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @NonNull
    private SharedPref sharedPref = new SharedPref(this);
    @NonNull
    private String[] posName = {"VeriFone 675", "VeriFone 520", "VeriFone e265"};
    @NonNull
    private int[] img = {R.drawable.vx675, R.drawable.vx520, R.drawable.verifone_e265};
    @NonNull
    private List<Home> arrayList = new ArrayList<>();


    @Nullable
    @BindView(R.id.homeRecyclerView)
    RecyclerView recyclerView;

   //@BindView(R.id.emailNavHeader)
  // TextView email;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

       // email.setText(sharedPref.getString("username"));

        int count = 0;

        for (String name : posName) {
            arrayList.add(new Home(name, img[count]));
            count++;
        }

        HomeAdapter adapter = new HomeAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);//toolbar add kuLeft
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            sharedPref.clear();
            LoginActivity.startIntent(this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_home) {

            Intent intent = new Intent(this,DrawerActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tutorials) {
            Intent intent = new Intent(this, TutorialsActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_FAQ) {

            Intent intent = new Intent(this, FAQActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

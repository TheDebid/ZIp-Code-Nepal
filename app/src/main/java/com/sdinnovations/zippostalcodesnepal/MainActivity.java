package com.sdinnovations.zippostalcodesnepal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Button b1 = findViewById(R.id.fbydistrict);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,FindByDistrict.class);
                startActivity(i);
            }
        });

        Button b2 = findViewById(R.id.mapofnepal);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MapsOfNepal.class);
                startActivity(i);
            }
        });

        Button b3 = findViewById(R.id.rate);
        b3.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                try {
                    Uri uri = Uri.parse("market://details?id="+getPackageName()+"");
                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goMarket);
                }catch (ActivityNotFoundException e){
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName()+"");
                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goMarket);
                }
            }
        });
        Button b4 = findViewById(R.id.about);
        b4.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                Intent i = new Intent(MainActivity.this,AboutApp.class);
                startActivity(i);
            }
        });



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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_rate) {
            try {
                Uri uri = Uri.parse("market://details?id=" + getPackageName() + "");
                Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(goMarket);
            } catch (ActivityNotFoundException e) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName() + "");
                Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(goMarket);
            }
        } else if (id == R.id.nav_feed) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "SDInnovationsNepal@gmail.com", null));
            String subject = "Feedback";
            String message = "Feedback Message: ";
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        } else if (id == R.id.nav_error) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "SDInnovationsNepal@gmail.com", null));
            String subject = "I found a bug";
            String message = " Bug Description: ";
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(MainActivity.this,AboutApp.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

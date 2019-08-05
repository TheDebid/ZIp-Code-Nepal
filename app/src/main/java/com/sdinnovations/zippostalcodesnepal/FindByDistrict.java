package com.sdinnovations.zippostalcodesnepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class FindByDistrict extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_district);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5994134925636771/2610397144");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        ListView listView = findViewById(R.id.districtList);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        ArrayList<String> dname = new ArrayList<>();
        dname = databaseAccess.getDisc();
        ListAdapter myListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dname);
        listView.setAdapter(myListAdapter);
        databaseAccess.close();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                Intent intent = new Intent(FindByDistrict.this, FindInsideDistrict.class);

                String districtname = (String) parent.getItemAtPosition(position);
                intent.putExtra("dname", districtname);
                startActivity(intent);
            }

        });

    }
}


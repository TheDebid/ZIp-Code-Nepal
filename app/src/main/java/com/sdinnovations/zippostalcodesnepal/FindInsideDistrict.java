package com.sdinnovations.zippostalcodesnepal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FindInsideDistrict extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_inside_district);

        final String districtname = getIntent().getStringExtra("dname");

        ListView listView = findViewById(R.id.insideList);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        ArrayList<String> DistrictName = new ArrayList<>();
        DistrictName = databaseAccess.getInsideDistrict(districtname);
        ListAdapter myListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DistrictName);
        listView.setAdapter(myListAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FindInsideDistrict.this, PostalDetails.class);

                String postalAddress = (String) parent.getItemAtPosition(position);
                intent.putExtra("postalAddress", postalAddress);
                intent.putExtra("districtname", districtname);

                startActivity(intent);
            }

        });


    }
}

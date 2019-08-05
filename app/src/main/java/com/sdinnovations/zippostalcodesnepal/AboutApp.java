package com.sdinnovations.zippostalcodesnepal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutApp extends AppCompatActivity {
    String companyname = "SD+Innovations";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        Button b1 = findViewById(R.id.moreapps);
        b1.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                try {
                    Uri uri = Uri.parse("market://developer?id="+companyname+"");
                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goMarket);
                }catch (ActivityNotFoundException e){
                    Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id="+companyname+"");
                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goMarket);
                }
            }
        });
        Button b2 = findViewById(R.id.pp);
        b2.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                Intent i = new Intent(AboutApp.this,Policy.class);
                startActivity(i);
            }
        });


    }
}

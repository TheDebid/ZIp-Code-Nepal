package com.sdinnovations.zippostalcodesnepal;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PostalDetails extends AppCompatActivity {
    TextView district, post_office, type, code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postal_details);

        district = findViewById(R.id.district);
        post_office = findViewById(R.id.post_office);
        type = findViewById(R.id.type);
        code=findViewById(R.id.code);

        final String postalAddress = getIntent().getStringExtra("postalAddress");
        final String districtname = getIntent().getStringExtra("districtname");


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String DistrictName = databaseAccess.getDistrictDetails(postalAddress,districtname);
        String ptype = databaseAccess.getType(postalAddress,districtname);
        final String pcode = databaseAccess.getCode(postalAddress,districtname);


        district.setText(DistrictName);
        post_office.setText(postalAddress);
        type.setText(ptype);
        code.setText(pcode);

        // Get clipboard manager object.
        Object clipboardService = getSystemService(CLIPBOARD_SERVICE);
        final ClipboardManager clipboardManager = (ClipboardManager)clipboardService;

        // This is the copy button.
        Button copyButton = (Button)findViewById(R.id.copycode);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a new ClipData.
              ClipData clipData = ClipData.newPlainText("Zip Code", pcode);
                // Set it as primary clip data to copy text to system clipboard.
                clipboardManager.setPrimaryClip(clipData);
                // Popup a snackbar.
                Snackbar snackbar = Snackbar.make(v, "Zip / Postal Code has been copied to clipboard.", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

    }
}
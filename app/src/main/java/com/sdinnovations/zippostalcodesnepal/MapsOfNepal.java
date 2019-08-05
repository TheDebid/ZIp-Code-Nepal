package com.sdinnovations.zippostalcodesnepal;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapsOfNepal extends AppCompatActivity {
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_of_nepal);

        WebView webview = findViewById(R.id.privacy);


        webview.loadUrl("file:///android_asset/map_of_nepal.html");
        webview.getSettings().setBuiltInZoomControls(true);
        webview.requestFocus();

        progressDialog = new ProgressDialog(MapsOfNepal.this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();

        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                try {
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

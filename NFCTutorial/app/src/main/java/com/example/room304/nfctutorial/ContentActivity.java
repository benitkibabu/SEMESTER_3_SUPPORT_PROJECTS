package com.example.room304.nfctutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {

    TextView stringTv, urlTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        stringTv = (TextView) findViewById(R.id.stringText);
        urlTv = (TextView) findViewById(R.id.urlText);

        if(getIntent().hasExtra("str") && getIntent().hasExtra("url")){
            stringTv.setText(getIntent().getStringExtra("str"));
            urlTv.setText(getIntent().getStringExtra("url"));
        }else{
            finish();
            return;
        }

    }
}

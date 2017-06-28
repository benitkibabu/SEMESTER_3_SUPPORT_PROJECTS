package com.example.room304.nfctutorial;

import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements NfcAdapter.CreateNdefMessageCallback {

    EditText stringET, urlET;

    NfcAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        adapter = NfcAdapter.getDefaultAdapter(this);
        if(adapter != null){
            if(!adapter.isEnabled()){
                Toast.makeText(this, "Please enable NFC!", Toast.LENGTH_LONG).show();
            }
        }else{
            finish();
            return;
        }


        stringET = (EditText) findViewById(R.id.stringText);
        urlET = (EditText) findViewById(R.id.urlText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        adapter.setNdefPushMessageCallback(this, this);

        //Use this for Sending Message Only!
        //WHen using this, you only need createNdefMessage() because we are only
        //Sending the pushMessage
        //adapter.setNdefPushMessage(createNdefMessage(), this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
   }

    @Override
    protected void onResume() {
        super.onResume();
        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())){
            //Process
            processIntent(getIntent());
        }
    }

    private void processIntent(Intent intent) {
        Parcelable[] parces = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if(parces != null){
            NdefMessage[] messages = new NdefMessage[parces.length];
            for(int i=0; i< parces.length; i++){
                messages[i] = (NdefMessage) parces[i];
            }

            Toast.makeText(this, "process Intent called", Toast.LENGTH_LONG).show();

            NdefMessage msg = messages[0];


            String text = new String(msg.getRecords()[0].getPayload());

            String url = new String(msg.getRecords()[1].getPayload());
            String date = new String(msg.getRecords()[2].getPayload());


            //Using this format we can remove the "EN" Language identifier from
            //The NdefRecord.createTextRecord() method.
            int index = text.indexOf("n")+1;
            text = text.substring(index, text.length());
            url = url.substring(index, url.length());
            date = date.substring(index, date.length());

            //this will prompt the user to open url in a browser
//            if (!url.startsWith("http://") && !url.startsWith("https://"))
//                url = "http://" + url;
//
//            Intent browserIntent =
//                    new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            startActivity(browserIntent);


            //Start a new activity to display the contents
            Intent i = new Intent(this, ContentActivity.class);
            i.putExtra("str", text);
            i.putExtra("url", url);
            i.putExtra("date", date);

            startActivity(i);
            finish();
        }
    }


    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String text = stringET.getText().toString();
        String url = urlET.getText().toString();
        String date = new Date().toString();

        NdefRecord[] records = new NdefRecord[]{
                NdefRecord.createTextRecord("en", text),
                NdefRecord.createTextRecord("en", url),
                NdefRecord.createTextRecord("en", date)
                //this will allow you to specify which Application to open when device receives
                //this message
//                ,NdefRecord.createApplicationRecord("com.example.room304.nfctutorial")
        };

        NdefMessage msg = new NdefMessage(records);

        return msg;
    }
}

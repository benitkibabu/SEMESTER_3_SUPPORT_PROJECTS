package com.example.room304.touchword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == event.ACTION_DOWN) {
                    TextView editText = (TextView) v;
                    float x = event.getX();
                    float y = event.getY();
                    int position = editText.getOffsetForPosition(x, y);

                    String text = tv.getText().toString();

                    getWord(text, position);
                }
                return true;
            }
        });
    }

    void getWord(String text, int position){
        List<Character> before = new ArrayList<Character>();
        List<Character> after = new ArrayList<Character>();
        StringBuffer newWord = new StringBuffer();

        for (int i = position; i >= 0; i--) {
            if (text.charAt(i) == ',' || text.charAt(i) == ' ') {
                break;
            }
            else {
                before.add(text.charAt(i));
            }
        }
        Log.i("Before", before.toString());

        for (int i = position+1; i <= text.length() - 1; i++) {
            if (text.charAt(i) == ',' || text.charAt(i) == ' ') {
                break;
            }
            else {
                after.add(text.charAt(i));
            }
        }
        Log.i("After", after.toString());

        for (int i = before.size() - 1; i >= 0; i--) {
            newWord.append(before.get(i));
        }
        for (int i = 0; i <= after.size() - 1; i++) {
            newWord.append(after.get(i));
        }
        String word = newWord.toString();

        Toast.makeText(MainActivity.this, newWord.toString(), Toast.LENGTH_SHORT).show();

    }
}

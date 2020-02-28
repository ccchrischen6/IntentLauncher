package com.example.intentlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /** receive the intent */
        Intent intent = getIntent();
        String[] info = intent.getStringArrayExtra("info");

        //set title text
        setTitle(info[0]);

        //set text for text view
        TextView secondText = (TextView) getView("Second");
        secondText.setText(info[1]);
    }

    //get View by its ID
    private View getView(String sID) {
        int ID = getResources().getIdentifier(sID, "id", getPackageName());
        return findViewById(ID);
    }
}

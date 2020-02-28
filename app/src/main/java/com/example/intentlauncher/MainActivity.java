package com.example.intentlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private String[] info = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void composeEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        infoGenerator();
        System.out.println(Arrays.toString(info));
        intent.putExtra(Intent.EXTRA_SUBJECT, info[0]);
        intent.putExtra(Intent.EXTRA_TEXT, info[1]);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //generate information, it is called whenever you press a button
    private void infoGenerator() {
        //capture two edit text
        EditText eTitle = (EditText) getView("E_1");
        EditText eMessage = (EditText) getView("E_2");

        //capture checkbox
        boolean onlyTitle = ((CheckBox) getView("C_1")).isChecked();

        String title = eTitle.getText().toString();
        String message = eMessage.getText().toString();

        //update info
        info[0] = title;
        info[1] = onlyTitle ? "" : message;
    }

    //get View by its ID
    private View getView(String sID) {
        int ID = getResources().getIdentifier(sID, "id", getPackageName());
        return findViewById(ID);
    }

    /** go to the second activity */
    public void sub(View view){
        infoGenerator();
        /** create an intent of target activity*/
        Intent intent = new Intent(this, SecondActivity.class);

        /** put the parameter to transfer*/
        intent.putExtra("info", info);
        startActivity(intent);
    }

    // call camera
    public void pic(View view){dispatchTakePictureIntent();}


    // camera intent module
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}

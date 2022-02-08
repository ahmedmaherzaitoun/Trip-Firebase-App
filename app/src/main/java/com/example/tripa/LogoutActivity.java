package com.example.tripa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LogoutActivity extends AppCompatActivity {

    AlertDialog.Builder dialog   ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        SharedPreferences sharedpreferences;
        sharedpreferences=getApplicationContext().getSharedPreferences("Preferences", 0);
        SharedPreferences.Editor editor = sharedpreferences.edit();


        dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure you want to logout ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editor.putString("LOGIN", null);
                editor.apply();
                Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
            }
        }).setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getBaseContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alert = dialog.create() ;
        alert.show();


    }
}
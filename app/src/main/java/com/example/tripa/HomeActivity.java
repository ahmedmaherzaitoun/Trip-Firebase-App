package com.example.tripa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.tripa.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

public class HomeActivity extends AppCompatActivity {

  //  Button btnLogout ;
    private long backPressedTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        SharedPreferences sharedpreferences;
        sharedpreferences=getApplicationContext().getSharedPreferences("Preferences", 0);
        SharedPreferences.Editor editor = sharedpreferences.edit();


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        NavController navController= Navigation.findNavController(this,R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
/*
        if(intent.getStringExtra("keyNot") != null) {
            Log.i("zatona keyNot", intent.getStringExtra("keyNot"));



                Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                i.putExtra("key" , intent.getStringExtra("keyNot") +"" );
                //important
                startActivity(i);
                // deatails activity ;
                Log.i("zatona notification", " tmam");

        }
*/




    }

    @Override
    public void onBackPressed() {
        if( backPressedTime + 2000 > System.currentTimeMillis()){
            // used to finish activity and parent (all app activity)
            finishAffinity();
            return;
        }else {
            Toast.makeText(getBaseContext(),"press back again to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return  true ;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id = item.getItemId();
        if( id == R.id.logout_icon){
            SharedPreferences sharedpreferences;
            sharedpreferences=getApplicationContext().getSharedPreferences("Preferences", 0);
            SharedPreferences.Editor editor = sharedpreferences.edit();

            AlertDialog.Builder  dialog = new AlertDialog.Builder(HomeActivity.this);
            dialog.setMessage("Are you sure you want to logout ?");
            dialog.setCancelable(true);
            dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    editor.putString("LOGIN", null);
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }
            }).setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alert = dialog.create() ;
            alert.show();
        }
        return true;
    }
}
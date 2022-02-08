package com.example.tripa;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.dvdb.materialchecklist.MaterialChecklist;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class DetailsActivity extends AppCompatActivity {
    List<PhotoMetadata> tripmetadata ;
    MaterialChecklist checklist;
    SharedPreferences sharedpreferences;
    FirebaseDatabase firebaseDatabase;
    private AlarmManager alarmManager;
    NotificationManager notificationManager;
    Calendar cal;
    DatabaseReference databaseReference;
    EditText nameedx , toedx,fromedx,descedx;
    String  names ,  tos ,  froms, descs ,items,dates;
    TextView tripdate;
    Button saveBTN,editDate,btnStart;
    Model Trip;
    DatabaseReference myRef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        createNotificationChannel();
        checklist =(MaterialChecklist)findViewById(R.id.checklist);
        nameedx = (EditText) findViewById(R.id.tripname);
        descedx = (EditText) findViewById(R.id.tripdesc);
        toedx = (EditText) findViewById(R.id.tripto);
        fromedx = (EditText) findViewById(R.id.tripfrome);
        saveBTN=(Button)findViewById(R.id.savebtn);
        tripdate = (TextView) findViewById(R.id.tripdate);
        editDate=(Button)findViewById(R.id.editdate);
        btnStart = findViewById(R.id.btn_start);
        Intent intent = getIntent();
        String id = intent.getStringExtra("key");

        final Calendar calendar = Calendar.getInstance();
        sharedpreferences=getApplicationContext().getSharedPreferences("Preferences", 0);
        String login = sharedpreferences.getString("LOGIN", null);
        System.out.println(login+"login");




        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(login).child("Trips").child(id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if( snapshot.exists()) {
                    String mname = snapshot.child("trip_name").getValue().toString();
                    String mdesc = snapshot.child("trip_desc").getValue().toString();
                    String mnotes = snapshot.child("trip_notes").getValue().toString();
                    String mTo = snapshot.child("trip_to").getValue().toString();
                    String mfrom = snapshot.child("trip_from").getValue().toString();

                    froms = mfrom;
                    tos = mTo;


                    String mdate = snapshot.child("trip_date").getValue().toString();
                    dates = mdate ;

                    System.out.println("mname " + mname);
                    nameedx.setText(mname);
                    descedx.setText(mdesc);
                    toedx.setText(mTo);
                    fromedx.setText(mfrom);
                    tripdate.setText(mdate);
                    checklist.setItems(mnotes);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //      myRef.child(login).child("Trips").child(id);

        //  checklist.setItems(items);
        String apiKey = getString(R.string.apikey);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }
        Intent i = getIntent();
        tripmetadata = (List<PhotoMetadata>) i.getSerializableExtra("photometadata");

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stPoint = froms;
                String enPoint = tos;
                try {
                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + stPoint + "/" + enPoint);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Log.i("zatona", " start");

                }catch (ActivityNotFoundException e ){
                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + stPoint + "/" + enPoint);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Log.i("zatona", " start");
                }
            }
        });
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("mark2");
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd|HH:mm");
                                tripdate.setText(simpleDateFormat.format(calendar.getTime()));
                                System.out.println(simpleDateFormat.format(calendar.getTime()));
                                dates = simpleDateFormat.format(calendar.getTime());
                            }
                        };

                        new TimePickerDialog(DetailsActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
                    }
                };
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        new DatePickerDialog(DetailsActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

                    }

                }, 100L);

            }
        });


        Trip = new Model();
       saveBTN.setOnClickListener(new View.OnClickListener() {
       @RequiresApi(api = Build.VERSION_CODES.O)
       @Override
       public void onClick(View view) {


           names = nameedx.getText().toString();
           descs = descedx.getText().toString();
           froms = fromedx.getText().toString();
           tos   = toedx.getText().toString();


           Trip.setTrip_date(dates);
           Trip.setTrip_desc(descs);
           Trip.setTrip_from(froms);
           Trip.setTrip_to(tos);
           Trip.setTrip_name(names);
           items = checklist.getItems(true,true);

           Trip.setTrip_notes(items);
           Log.i("zatona", " save");


           Trip.setTripid(id);
           SharedPreferences sharedpreferences;
           //important
           AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);

           sharedpreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
           String login = sharedpreferences.getString("LOGIN", null);
           //myRef.updateChildren(Trip).;
           if( Trip.getTrip_date() == null || Trip.getTrip_name() == null || Trip.getTrip_from() == null || Trip.getTrip_to() == null || Trip.getTrip_date().equals("")  || Trip.getTrip_name().equals("") || Trip.getTrip_from().equals("")  || Trip.getTrip_to().equals("") ){
               Toast.makeText(getApplicationContext(),"complete informations",Toast.LENGTH_SHORT).show();
           }else {
               myRef.child("trip_date").setValue(Trip.getTrip_date());
               myRef.child("trip_desc").setValue(Trip.getTrip_desc());
               myRef.child("trip_name").setValue(Trip.getTrip_name());

               myRef.child("trip_from").setValue(Trip.getTrip_from());
               myRef.child("trip_to").setValue(Trip.getTrip_to());
               myRef.child("trip_notes").setValue(Trip.getTrip_notes());
               // myRef.child("arrayphotometa").setValue(tripmetadata);
           }

           // TRUE TRUE /*



           SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd|HH:mm");
           Date tripDate = null ;


           try {
               tripDate = sdf.parse(Trip.getTrip_date());
           } catch (ParseException e) {
               e.printStackTrace();
           }
           cal = null;
           cal=Calendar.getInstance();
           cal.setTime(tripDate);

         //  PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(), 0,in,0);

           Intent intent1 = new Intent( getApplicationContext(), HomeActivity.class);
           intent1.putExtra("keyNot" , intent.getStringExtra("key") +"");

          // PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);


           TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
           stackBuilder.addParentStack(HomeActivity.class);
           stackBuilder.addNextIntent(intent1);

           PendingIntent pendingIntent;
           pendingIntent = stackBuilder
                   .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

           Calendar calendar = Calendar.getInstance();
           calendar.setTimeInMillis(System.currentTimeMillis());
           calendar.set(Calendar.HOUR_OF_DAY, 7);
           calendar.set(Calendar.MINUTE, 0);
           calendar.set(Calendar.SECOND, 1);


           alarmManager = (AlarmManager) getSystemService( Context.ALARM_SERVICE);
           alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis() ,pendingIntent);

           NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),"Tripa")
                   .setSmallIcon(R.drawable.ic_baseline_alarm_24)
                   .setContentTitle("Trip: " + Trip.getTrip_name()  )
                   .setAutoCancel(true)
                   .setDefaults(NotificationCompat.DEFAULT_ALL)
                   .setPriority(NotificationCompat.PRIORITY_HIGH)
                   .setContentIntent(pendingIntent);


           Log.i("zatona", "save");

           NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
           notificationManagerCompat.notify(123,builder.build());

           Toast.makeText(view.getContext(),"Trip is saved",Toast.LENGTH_SHORT).show();



           // myRef.child("arrayphotometa").setValue(tripmetadata);

           System.out.println("DOne");





       }
   });










    }
    public static void startAlarmBroadcastReceiver(Context context) {

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name="REMINDER NOTIFICATION";
            String description="It's Trip Time";
            int importance= NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel =new NotificationChannel("Tripa",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public void onError(@NonNull Status status) {
        // TODO: Handle the error.
        Toast.makeText(getApplicationContext(), status.toString(), Toast.LENGTH_SHORT).show();
    }}






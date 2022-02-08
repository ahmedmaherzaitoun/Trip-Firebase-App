package com.example.tripa;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private View view;
    private ArrayList<Model> trips ;
    private MyAdapter adaptor ;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView =  view.findViewById(R.id.userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        trips = new ArrayList<>();

        adaptor = new MyAdapter(trips);
        recyclerView.setAdapter(adaptor);

        SharedPreferences sharedpreferences;

        sharedpreferences=getContext().getSharedPreferences("Preferences", 0);
        String login = sharedpreferences.getString("LOGIN", null);


        databaseReference  = FirebaseDatabase.getInstance().getReference("Users").child(login).child("Trips");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Model trip = dataSnapshot.getValue(Model.class);
                    // compare datatrip and and current data to put finished trip in history
                    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd|HH:mm");
                    Date tripDate = null ;
                    String strCurrentDate = null;
                    Date currentDate = null;
                    try {
                        if( dataSnapshot.exists() && trip.getTrip_date() != null) {
                            tripDate = sdf.parse(trip.getTrip_date());
                            strCurrentDate = new SimpleDateFormat("yy-MM-dd|HH:mm", Locale.getDefault()).format(new Date());

                            currentDate = sdf.parse(strCurrentDate);

                            if( currentDate.compareTo(tripDate) <= 0){
                                Log.i("zatona", strCurrentDate + " " + trip.getTrip_date());
                                trips.add(trip);
                            }
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                adaptor.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return view;
    }
}
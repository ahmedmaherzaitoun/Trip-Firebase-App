package com.example.tripa;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private  ArrayList< Model> trips ;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;
    public MyAdapter(ArrayList<Model> trips) {
        this.trips = trips;
        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
    }
    @NonNull
    @Override


    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {

        holder.getTextViewName().setText(trips.get(position).getTrip_name());
        holder.getTextViewDate().setText(trips.get(position).getTrip_date());
        holder.getTextViewFrom().setText(trips.get(position).getTrip_from());
        holder.getTextViewTo().setText(trips.get(position).getTrip_to());
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( view.getContext(), DetailsActivity.class);
                //important
                DatabaseReference databaseReference;

                intent.putExtra("key", trips.get(position).getTripid());
                view.getContext().startActivity(intent);
                // deatails activity ;
                Log.i("zatona", "details btn");


            }
        });
        holder.btnStartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stPoint = trips.get(position).getTrip_from();
                String enPoint = trips.get(position).getTrip_to();
                try {
                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + stPoint + "/" + enPoint);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);
                    Log.i("zatona", " start");


                }catch (ActivityNotFoundException e ){
                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + stPoint + "/" + enPoint);


                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);
                    Log.i("zatona", " start");

                }

            }
        });
    }





    @Override
    public int getItemCount() {
        return trips.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private Button btnDetails ;
        private Button btnStartHome;
        private TextView textViewTo ;
        private  TextView textViewFrom ;
        private TextView textViewDate ;
        private String tripId ;

        public String getTripId() {
            return tripId;
        }

        public void setTripId(String tripId) {
            this.tripId = tripId;
        }

        // TextView textViewTime ;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            if(textViewName == null){
                textViewName = itemView.findViewById(R.id.textView_name1);
            }
            if( btnDetails == null){
                btnDetails= itemView.findViewById(R.id.btn_details);
            }
            if( btnStartHome == null){
                btnStartHome= itemView.findViewById(R.id.btn_start_home);
            }

            if(textViewDate == null){
                textViewDate = itemView.findViewById(R.id.textView_date1);
            }
            if(textViewFrom == null){
                textViewFrom = itemView.findViewById(R.id.textView_from1);
            }
            if(textViewTo == null){
                textViewTo = itemView.findViewById(R.id.textView_to1);
            }




        }

        public TextView getTextViewName() {

            if(textViewName == null){
                textViewName = itemView.findViewById(R.id.textView_name1);
            }
            return textViewName;
        }




        public TextView getTextViewDate() {
            if(textViewDate == null){
                textViewDate = itemView.findViewById(R.id.textView_date1);
            }
            return textViewDate ;
        }
        public TextView getTextViewFrom() {
            if(textViewFrom == null){
                textViewFrom = itemView.findViewById(R.id.textView_from1);
            }
            return textViewFrom ;
        }
        public TextView getTextViewTo() {

            if(textViewTo == null){
                textViewTo = itemView.findViewById(R.id.textView_to1);
            }
            return textViewTo ;
        }






    }
}

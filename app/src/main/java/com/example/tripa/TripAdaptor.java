package com.example.tripa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TripAdaptor extends RecyclerView.Adapter<TripAdaptor.ViewHolder> {
    private  ArrayList< Model> trips ;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    public TripAdaptor(ArrayList<Model> trips) {

        this.trips = trips;
        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
    }
    @NonNull
    @Override


    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_list_item,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull  TripAdaptor.ViewHolder holder, int position) {
        holder.getTextViewName().setText(trips.get(position).getTrip_name());
        holder.getTextViewDesc().setText(trips.get(position).getTrip_desc());
        holder.getTextViewDate().setText(trips.get(position).getTrip_date());
        holder.getTextViewFrom().setText(trips.get(position).getTrip_from());
        holder.getTextViewTo().setText(trips.get(position).getTrip_to());

        holder.getImDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedpreferences;

                sharedpreferences = view.getContext().getSharedPreferences("Preferences", 0);
                String login = sharedpreferences.getString("LOGIN", null);


                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(login).child("Trips").child(trips.get(position).getTripid());
                databaseReference.removeValue();

                notifyDataSetChanged();





            }
        });



        //   holder.getTextViewTime().setText(trips.get(position).getTrip_time());

    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    /*
        private ArrayList<Model>  models  ;

        private Context context ;
        public ModelAdaptor( Context context ,  ArrayList<Model> models) {
            super(context, R.layout.ly_list_item, R.id.textView_name, models);
            this.context = context ;
            this.models = models ;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView ;
            ViewHolder viewHolder ;
            if( view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = inflater.inflate(R.layout.ly_list_item, parent, false);
                viewHolder = new ViewHolder( view);
                view.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.getTextViewName().setText(models.get(position).getTrip_name());
            viewHolder.getTextViewDescrption().setText(models.get(position).getTrip_desc());
            viewHolder.getTextViewDate().setText(models.get(position).getTrip_date());
            viewHolder.getTextViewFrom().setText(models.get(position).getTrip_from());
            viewHolder.getTextViewTo().setText(models.get(position).getTrip_to());
            return  view;
        }*/
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDesc ;
        TextView textViewTo ;
        TextView textViewFrom ;
        TextView textViewDate ;
        public ImageView imDelete ;
        String tripId ;

        public String getTripId() {
            return tripId;
        }

        public void setTripId(String tripId) {
            this.tripId = tripId;
        }

        // TextView textViewTime ;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            if(textViewName == null){
                textViewName = itemView.findViewById(R.id.textView_name1);
            }
            if( textViewDesc == null){
                textViewDesc= itemView.findViewById(R.id.btn_details);
            }

            //  if( textViewTime == null){
            //    textViewTime= itemView.findViewById(R.id.textView_time);
            //  }
            if(textViewDate == null){
                textViewDate = itemView.findViewById(R.id.textView_date1);
            }
            if(textViewFrom == null){
                textViewFrom = itemView.findViewById(R.id.textView_from1);
            }
            if(textViewTo == null){
                textViewTo = itemView.findViewById(R.id.textView_to1);
            }
            if(imDelete == null){
                imDelete = itemView.findViewById(R.id.delete);
            }



        }

        public TextView getTextViewName() {

            if(textViewName == null){
                textViewName = itemView.findViewById(R.id.textView_name1);
            }
            return textViewName;
        }



        public TextView getTextViewDesc() {
            if( textViewDesc == null){
                textViewDesc= itemView.findViewById(R.id.btn_details);
            }
            return textViewDesc;
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

        public ImageView getImDelete() {
            if(imDelete == null){
                imDelete = itemView.findViewById(R.id.delete);
            }

            return imDelete;
        }
       /*
        public TextView getTextViewTime() {

            if(textViewTime == null){
                textViewTime = itemView.findViewById(R.id.textView_time);
            }
            return textViewTime ;
        }*/




    }
}

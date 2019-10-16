package com.example.skittles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.firebase.client.Firebase;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;



import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.FirebaseDatabase;

//import com.google.firebase.Firebase;


public class student_sprint_objective extends AppCompatActivity {
    TextView sprintObjective;
    TextView sprintReview;
    private  Firebase mref;
    private  Firebase mref1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sprint_objective);

        sprintObjective=(TextView)findViewById(R.id.sprintObjView);
        sprintReview=(TextView)findViewById(R.id.sprintReviewView);
        Firebase.setAndroidContext(this);

        mref=new Firebase("https://skittlessdp.firebaseio.com/SprintInformation/-Lr1vZr3OsDrMwbjWzdl/sprintReview");
        mref1=new Firebase("https://skittlessdp.firebaseio.com/SprintInformation/-Lr1vZr3OsDrMwbjWzdl/sprintObjective");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value=dataSnapshot.getValue(String.class);

                sprintReview.setText(value);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value2=dataSnapshot.getValue(String.class);
                sprintObjective.setText(value2);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }
}
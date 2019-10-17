package com.example.skittles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {



    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onProductOwner(View view){

        Intent productOwner=new Intent(this,product_owner_menu.class);
        startActivity(productOwner);
    }

    public void onStudent(View view){

        Intent student=new Intent(this,student_menu.class);
        startActivity(student);
    }
    public void onLecturer(View view){

        Intent onlecturer=new Intent(this,lecture_marking_screen.class);
        startActivity(onlecturer);
    }
    public void onSignOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("finish", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        startActivity(intent);
        finish();
    }
}
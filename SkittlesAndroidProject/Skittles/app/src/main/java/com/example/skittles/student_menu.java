package com.example.skittles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class student_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
    }

    public void onSprint(View view){

        Intent onsprint=new Intent(this,student_sprint_objective.class);
        startActivity(onsprint);
    }
    public void onUserStories(View view){

        Intent onuserstories=new Intent(this,user_stories.class);
        startActivity(onuserstories);
    }
}

package com.example.skittles;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sprintobjective extends AppCompatActivity {

    EditText teamName;
    TextInputEditText sprintReview;
    TextInputEditText sprintObjective;
    EditText sprintName;
    EditText sprintStartDate;
    EditText sprintEndDate;
    Button   submit;
    DatabaseReference reff;
    SprintInformation sprintInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprintobjective);
        teamName=(EditText)findViewById(R.id.teamName1);
        sprintReview=(TextInputEditText) findViewById(R.id.sprintReview1);
        sprintObjective=(TextInputEditText)findViewById(R.id.sprintObjective1);
        sprintName=(EditText)findViewById(R.id.sprintNumber);
        sprintStartDate=(EditText)findViewById(R.id.startDate);
        sprintEndDate=(EditText)findViewById(R.id.endDate);
        submit=(Button)findViewById(R.id.scrumButton);
        submit.setOnClickListener(new sprintInfor());
        reff=FirebaseDatabase.getInstance().getReference().child("SprintInformation");

    }

    private class sprintInfor implements View.OnClickListener{


        public void onClick(View view){
            sprintInformation=new SprintInformation();

            String line1=teamName.getText().toString().trim();
            String line2=sprintReview.getText().toString().trim();
            String line3=sprintObjective.getText().toString().trim();
            String line4=sprintName.getText().toString().trim();
            String line5=sprintStartDate.getText().toString().trim();
            String line6=sprintEndDate.getText().toString().trim();
            if(line1.length()>0 && line2.length()>0 && line3.length()>0 && line4.length()>0 && line5.length()>0 && line6.length()>0) {
                sprintInformation.setTeamName(teamName.getText().toString().trim());
                sprintInformation.setSprintReview(sprintReview.getText().toString().trim());
                sprintInformation.setSprintObjective(sprintObjective.getText().toString().trim());
                sprintInformation.setSprintName(sprintName.getText().toString().trim());
                sprintInformation.setStartDate(sprintStartDate.getText().toString().trim());
                sprintInformation.setEndDate(sprintEndDate.getText().toString().trim());
                reff.push().setValue(sprintInformation);
                Toast.makeText(sprintobjective.this, "Sprint Created ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(sprintobjective.this, successproductowner.class);
                startActivity(intent);
            }else{
                Toast.makeText(
                        sprintobjective.this,
                        " Error Please Fill All Fields",
                        Toast.LENGTH_LONG).show();

            }

        }


    }

}

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

public class createproject extends AppCompatActivity {
   // private FirebaseStorage storage=FirebaseStorage.getInstance();
    EditText projectName;
    EditText numOfTeams;
    TextInputEditText projectDescription;
    Button createProject;
    DatabaseReference reff;
    Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createproject);
        projectName=(EditText)findViewById(R.id.createProject);
        numOfTeams=(EditText)findViewById(R.id.maxNoTeams);
        projectDescription=( TextInputEditText)findViewById(R.id.projectDetailed1);
        createProject=(Button)findViewById(R.id.buttonCreate1);

        reff=FirebaseDatabase.getInstance().getReference().child("Project");
        createProject.setOnClickListener(new upLoad());
    }

    private class upLoad implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            project=new Project();
            String line1=projectName.getText().toString().trim();
            String line2=projectDescription.getText().toString().trim();
            String line3=numOfTeams.getText().toString().trim();
            if(line1.length()>0 && line2.length()>0 && line3.length()>0) {
                int numOfTeamsCon=Integer.parseInt(numOfTeams.getText().toString().trim());
                project.setProjectName(projectName.getText().toString().trim());
                project.setMaxNoteams(numOfTeamsCon);
                project.setProjectDescr(projectDescription.getText().toString().trim());
                reff.push().setValue(project);
                Toast.makeText(createproject.this, "Project create ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(createproject.this, successproductowner.class);
                startActivity(intent);
            }else{

                Toast.makeText(
                        createproject.this,
                        " Error Please Fill All Fields",
                        Toast.LENGTH_LONG).show();
            }

        }
    }

}

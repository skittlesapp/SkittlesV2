package com.example.skittles;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class productbacklog extends AppCompatActivity {

    TextInputEditText productBackLog1;
    Button btnProductBackLog;
    DatabaseReference reff;
    ProductBackLog productBackLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productbacklog);
        productBackLog1=(TextInputEditText)(findViewById(R.id.backlogEditor1));
        btnProductBackLog=(Button)(findViewById(R.id.buttonBackLog));
        btnProductBackLog.setOnClickListener(new productBackLog());
    }


    private  class productBackLog implements View.OnClickListener{

        public  void onClick(View view){
            productBackLog=new ProductBackLog();

            reff=FirebaseDatabase.getInstance().getReference().child("ProductBackLog");
            String line=productBackLog1.getText().toString().trim();
            if(line.length()>0){
                productBackLog.setBacklog(productBackLog1.getText().toString().trim());
                reff.push().setValue(productBackLog);
             //   reff.push().updateChildren(productbacklog);
                Toast.makeText(productbacklog.this, "Project create ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(productbacklog.this, successproductowner.class);
                startActivity(intent);


            }else{

                Toast.makeText(
                        productbacklog.this,
                        " Error Please Fill All Fields",
                        Toast.LENGTH_LONG).show();
            }

        }


    }
}

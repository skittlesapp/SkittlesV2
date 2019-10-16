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

public class productDetails extends AppCompatActivity {

    TextInputEditText productDetailedDescription;
    TextInputEditText sprintRules;
    Button productSubmit;
    DatabaseReference reff;
    ProductDescription productDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        productDetailedDescription=(TextInputEditText)findViewById(R.id.productDetailed1);
        sprintRules=(TextInputEditText)findViewById(R.id.sprintRules1);
        productSubmit=(Button)findViewById(R.id.detailSubmit);
        productSubmit.setOnClickListener(new productDescription());
        reff=FirebaseDatabase.getInstance().getReference().child("ProductDescription");
    }

    private class productDescription implements View.OnClickListener{


        public void onClick(View view ){

            String line1=productDetailedDescription.getText().toString().trim();
            String line2= sprintRules.getText().toString().trim();

            if(line1.length()>0 && line2.length()>0) {

                productDescription = new ProductDescription();
                productDescription.setSprintRules(sprintRules.getText().toString().trim());
                productDescription.setProductDescription(productDetailedDescription.getText().toString().trim());
                reff.push().setValue(productDescription);
                Toast.makeText(productDetails.this, "Project Details created ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(productDetails.this, successproductowner.class);
                startActivity(intent);
            }else{

                Toast.makeText(
                        productDetails.this,
                        " Error Please Fill All Fields",
                        Toast.LENGTH_LONG).show();
            }

        }

    }


}

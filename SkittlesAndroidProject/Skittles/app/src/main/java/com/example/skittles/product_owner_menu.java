package com.example.skittles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class product_owner_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_owner_menu);
    }

    public void onProduct(View view){

        Intent createProject=new Intent(this,createproject.class);
        startActivity(createProject);
    }
    public void  onProductDes(View view){

        Intent productDes=new Intent(this,productDetails.class);
        startActivity(productDes);

    }
    public void onScrum(View view){

        Intent onscrum=new Intent(this,sprintobjective.class);
        startActivity(onscrum);

    }

    public  void onProductBackLog(View view){
        Intent i =new Intent(this,productbacklog.class);
        startActivity(i);

    }

}

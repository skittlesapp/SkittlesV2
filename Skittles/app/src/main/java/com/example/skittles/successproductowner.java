package com.example.skittles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class successproductowner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successproductowner);


    }

    public void onSuccess(View view){

        Intent success=new Intent(this,product_owner_menu.class);
        startActivity(success);
    }
    public void onHome(View view){

        Intent successHome=new Intent(this,MainActivity.class);
        startActivity(successHome);
    }
}

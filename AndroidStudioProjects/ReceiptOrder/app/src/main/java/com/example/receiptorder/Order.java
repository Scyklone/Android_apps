package com.example.receiptorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Order extends AppCompatActivity {


    private Button button;
    private Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        ImageButton I = (ImageButton) findViewById(R.id.AveInfo);
        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Order.this, Pop.class));
            }
        });

        I = (ImageButton) findViewById(R.id.aveleImg2);
        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Order.this, Pop.class));

            }
        });

        I = (ImageButton) findViewById(R.id.tylenolImg2);
        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Order.this, Pop.class));
            }
        });

        I = (ImageButton) findViewById(R.id.tylenolImage);
        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Order.this, Pop.class));
            }
        });



    }




   // public void ProductMe(View view){
        ///Product myProduct = Product.makeText(this, message, duration);
       // Product myProduct = Product.maketext
        //myProduct.show();
    //}

    public void toastMe(View view){
        ///Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "No Products at the moment",Toast.LENGTH_SHORT);
        myToast.show();
    }




}

package com.example.receiptorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.orderButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrder();
            }
        });

        button = (Button) findViewById(R.id.info_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfo();
            }
        });


    }

    public void openOrder() {
        Intent intent = new Intent(this, Order.class);
        startActivity(intent);

    }

    public void openInfo() {
        Intent intent = new Intent(this, InfoPage.class);
        startActivity(intent);
    }




    public void toastMe(View view){
        ///Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "Pollos Hermanos",Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void countMe(View view){


        //get the text view
        TextView showCountTextView = (TextView) findViewById(R.id.textView);

        // Get the value of the text view.
        String countString = showCountTextView.getText().toString();

        // Convert value to a number and increment it.
        Integer count = Integer.parseInt(countString);
        count++;

        // Display the new value in the text view.
        showCountTextView.setText(count.toString());



    }

    public void resetMe(View view){
        //get the text view
        TextView showCountTextView = (TextView) findViewById(R.id.textView);

        // Get the value of the text view.
        String countString = showCountTextView.getText().toString();

        // Convert value to a number and increment it.
        Integer count = Integer.parseInt(countString);
        count = 0;

        // Display the new value in the text view.
        showCountTextView.setText(count.toString());
    }

    public void randomMe(View view) {
        //Create an Intent to start the second activity
        Intent randomIntent =  new Intent(this, Order.class);

        //Start the new activity
        startActivity(randomIntent);
    }
}

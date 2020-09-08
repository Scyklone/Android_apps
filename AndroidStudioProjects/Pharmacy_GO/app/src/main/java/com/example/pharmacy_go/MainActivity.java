package com.example.pharmacy_go;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn_product);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProduct();
            }
        });
    }

    public void sendprescriptActivity(View view) {
        Intent sendPres = new Intent(this, SendPerscriptionActivity.class);
        startActivity(sendPres);
    }

    public void openProduct() {
        Intent intent = new Intent(this, Product.class);
        startActivity(intent);

    }
}

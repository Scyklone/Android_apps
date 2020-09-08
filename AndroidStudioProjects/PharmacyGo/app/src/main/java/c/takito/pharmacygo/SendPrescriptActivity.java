package c.takito.pharmacygo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SendPrescriptActivity extends AppCompatActivity {

    private EditText etxtPharmacy;
    private EditText etxtPrescriptData;
    protected Button sendEmailButton;
    protected Button takePictureButton;
    private ImageView imageView;

    Uri imageuri = null;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @SuppressLint({"WrongViewCast", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_prescript);

        etxtPharmacy = findViewById(R.id.etxt_pharmacyemail);
        etxtPrescriptData = findViewById(R.id.etxt_planmedico);
        sendEmailButton = findViewById(R.id.btn_sendemail);
        takePictureButton = findViewById(R.id.btn_takepic);
        imageView = findViewById(R.id.imageView);

        dispatchTakePictureIntent();

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private  void sendMail() {
        String recipientList = etxtPharmacy.getText().toString();
        String[] recipients = recipientList.split(",");
        String message = etxtPrescriptData.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, "PRESCRIPTION");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("image/jpeg");

        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}

package com.example.pharmacy_go;

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

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class SendPerscriptionActivity extends AppCompatActivity {

    private EditText pharmacyEmailEditText;
    private EditText medicalPlanEditText;
    private TextView imagePathTextView;
    protected Button sendEmailButton;
    protected Button choosePrescriptionButton;
    protected Button takePictureButton;

    Uri imageUri = null;
    String pathToFile;

    boolean clickButtonPicture = false;
    static final int RQS_LOADIMAGE = 0;
    static final int RQS_SENDEMAIL = 1;
    static final int RQS_TAKEPIC = 2;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String IMAGE_DIRECTORY = "/PharmacyGo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_perscription);

        pharmacyEmailEditText = findViewById(R.id.etxt_pharmacyemail);
        medicalPlanEditText = findViewById(R.id.etxt_planmedico);
        sendEmailButton = findViewById(R.id.btn_sendemail);
        choosePrescriptionButton = findViewById(R.id.btn_choose);
        takePictureButton = findViewById(R.id.btn_takepic);
        imagePathTextView = findViewById(R.id.imgPath);

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }


        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                clickButtonPicture = true;
            }
        });

        choosePrescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RQS_LOADIMAGE);
            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && clickButtonPicture == true) {

                Bitmap thumbnail = BitmapFactory.decodeFile(pathToFile);
                //imageView.setImageBitmap(thumbnail);
                saveImage(thumbnail);
                Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
            }
            switch (requestCode) {
                case RQS_LOADIMAGE:
                    imageUri = data.getData();
                    imagePathTextView.setText(imageUri.toString());
                    break;
                case RQS_SENDEMAIL:
                    break;
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takepicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takepicture.resolveActivity(getPackageManager()) != null) {
            File photoFile;
            photoFile = createPhotoFile();
            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(this, "c.takito.pharmacygo.fileprovider", photoFile);
                takepicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takepicture, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        } catch (IOException e) {
            Log.d("my_log", "Excep : " + e.toString());
        }
        return image;
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this, new String[]{f.getPath()}, new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void sendMail() {
        String recipientList = pharmacyEmailEditText.getText().toString();
        String[] recipients = recipientList.split(",");
        String message = medicalPlanEditText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, "PRESCRIPTION");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (imageUri != null) {
            intent.putExtra(Intent.EXTRA_STREAM, imageUri);
            intent.setType("image/png");
        } else {
            intent.setType("plain/text");
        }
        startActivity(Intent.createChooser(intent, "Choose an email: "));
    }
}
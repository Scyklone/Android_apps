package c.takito.pharmacygo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void mainActivity(View view) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void signinActivity(View view) {
        Intent signin = new Intent(this, SignInActivity.class);
        startActivity(signin);
    }
}

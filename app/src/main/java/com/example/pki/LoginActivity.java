package com.example.pki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pki.models.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public void login(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        User loggedUser = getLoggedUser();

        if (loggedUser.getUsername().equals(username.getText().toString())
                && loggedUser.getPassword().equals(password.getText().toString())) {
            Intent intent = new Intent(this, PackageActivity.class);
            intent.putExtra("index", 0);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Neuspesna prijava", Toast.LENGTH_SHORT).show();
        }
    }

    private User getLoggedUser() {
        User loggedUser = Util.getLoggedUser(this);
        return loggedUser;
    }
}
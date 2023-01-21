package com.example.pki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pki.models.User;

public class UserPasswordActivity extends AppCompatActivity {

    User loggedUser;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_password);

        Toolbar toolbar = findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView textView = findViewById(R.id.back_toolbar_text);
        textView.setText("Promena lozinke");

        loggedUser = getLoggedUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back) {
            Intent intent = new Intent(this, PackageActivity.class);
            intent.putExtra("index", 0);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changePassword(View view) {
        EditText old_password = findViewById(R.id.old_password);
        EditText new_password = findViewById(R.id.new_password);

        String old_password_text = old_password.getText().toString();
        String new_password_text = new_password.getText().toString();

        if ("".equals(old_password_text) || "".equals(new_password_text)) {
            Toast.makeText(this, "Oba polja moraju biti popunjena!", Toast.LENGTH_SHORT).show();
        } else if (!old_password_text.equals(loggedUser.getPassword())) {
            Toast.makeText(this, "Nije ispravna stara lozinka!", Toast.LENGTH_SHORT).show();
            old_password.setText("");
            new_password.setText("");
        } else if (old_password_text.equals(new_password_text)) {
            Toast.makeText(this, "Nova lozinka mora biti drugacija!", Toast.LENGTH_SHORT).show();
            new_password.setText("");
        } else {
            setLoggedUserPassword(new_password_text);
            Intent intent = new Intent(this, PackageActivity.class);
            intent.putExtra("index", 0);
            startActivity(intent);
            Toast.makeText(this, "Promenjena lozinka!", Toast.LENGTH_SHORT).show();
        }
    }

    private User getLoggedUser() {
        User loggedUser = Util.getLoggedUser(this);
        return loggedUser;
    }

    private void setLoggedUserPassword(String new_password) {
        loggedUser.setPassword(new_password);
        Util.setLoggedUser(this, loggedUser);
    }
}
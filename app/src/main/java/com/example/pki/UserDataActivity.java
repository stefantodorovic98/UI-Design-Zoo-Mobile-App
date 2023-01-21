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

public class UserDataActivity extends AppCompatActivity {

    private User loggedUser;

    private EditText firstname;
    private EditText lastname;
    private EditText phone;
    private EditText address;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        Toolbar toolbar = findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView textView = findViewById(R.id.back_toolbar_text);
        textView.setText("Promena podataka");

        loggedUser = getLoggedUser();

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);

        firstname.setText(loggedUser.getFirstname());
        lastname.setText(loggedUser.getLastname());
        phone.setText(loggedUser.getPhone());
        address.setText(loggedUser.getAddress());
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

    public void changeData(View view) {
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        String firstname_text = firstname.getText().toString();
        String lastname_text = lastname.getText().toString();
        String phone_text = phone.getText().toString();
        String address_text = address.getText().toString();

        if ("".equals(firstname_text) || "".equals(lastname_text) || "".equals(phone_text) || "".equals(address_text)) {
            Toast.makeText(this, "Sva polja moraju biti popunjena!", Toast.LENGTH_SHORT).show();
        } else if (firstname_text.equals(loggedUser.getFirstname()) && lastname_text.equals(loggedUser.getLastname())
                && phone_text.equals(loggedUser.getPhone()) && address_text.equals(loggedUser.getAddress())) {
            Intent intent = new Intent(this, PackageActivity.class);
            intent.putExtra("index", 0);
            startActivity(intent);
            Toast.makeText(this, "Ostali su isti podaci!", Toast.LENGTH_SHORT).show();
        } else {
            saveLoggedUserData(firstname_text, lastname_text, phone_text, address_text);
            Intent intent = new Intent(this, PackageActivity.class);
            intent.putExtra("index", 0);
            startActivity(intent);
            Toast.makeText(this, "Promenjeni podaci!", Toast.LENGTH_SHORT).show();
        }

    }

    private User getLoggedUser() {
        User loggedUser = Util.getLoggedUser(this);
        return loggedUser;
    }

    private void saveLoggedUserData(String firstname, String lastname, String phone, String address) {
        loggedUser.setFirstname(firstname);
        loggedUser.setLastname(lastname);
        loggedUser.setPhone(phone);
        loggedUser.setAddress(address);
        Util.setLoggedUser(this, loggedUser);
    }
}
package com.example.pki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pki.models.Packet;

import java.util.List;

public class PackageActivity extends AppCompatActivity {

    private List<Packet> packetList;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        Toolbar toolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        packetList = getPackets();

        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);

        loadDataFromList();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Da se vidi ikonica u meniju
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        if (id == R.id.pregled_desavanja) {
            intent = new Intent(this, EventActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.pregled_zivotinja) {
            intent = new Intent(this, AnimalListActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.obavestenja) {
            intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.kontakt_informacije) {
            intent = new Intent(this, ContactActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.promena_podataka) {
            intent = new Intent(this, UserDataActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.promena_lozinke) {
            intent = new Intent(this, UserPasswordActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.odjava) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void onClickLeft(View view) {
        if (index == 0) {
            index = 2;
        } else index--;
        loadDataFromList();
    }

    public void onClickRight(View view) {
        if (index == 2) {
            index = 0;

        } else index++;
        loadDataFromList();
    }

    private List<Packet> getPackets() {
        List<Packet> packetList = Util.getPackets(this);
        return packetList;
    }

    private void loadDataFromList() {
        ImageView image = findViewById(R.id.packet_image);
        TextView name = findViewById(R.id.packet_name);
        TextView description = findViewById(R.id.packet_description);

        image.setImageResource(packetList.get(index).getImage());
        name.setText(packetList.get(index).getName());
        description.setText(packetList.get(index).getDescription());
    }

    public void onSingleTicketBuy(View view) {
        Intent intent = new Intent(this, SingleTicketActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void onGroupTicketBuy(View view) {
        Intent intent = new Intent(this, GroupTicketActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }
}
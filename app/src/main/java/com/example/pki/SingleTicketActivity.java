package com.example.pki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pki.models.Packet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleTicketActivity extends AppCompatActivity {

    private List<Packet> packetList;

    private int index = 0;

    private final Map<Integer, Integer> colorMap = new HashMap<Integer, Integer>(){{
        put(0, R.color.bronze);
        put(1, R.color.silver);
        put(2, R.color.gold);
    }};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_ticket);

        Toolbar toolbar = findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView textView = findViewById(R.id.back_toolbar_text);
        textView.setText("Ulaznice i paketi");

        packetList = getPackets();

        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        loadDataFromList();
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
            intent.putExtra("index", index);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buySingleTicket(View view) {
        EditText promo = findViewById(R.id.ticket_promo);
        String promo_text = promo.getText().toString();

        if ("".equals(promo_text)) {
            Intent intent = new Intent(this, PackageActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
            Toast.makeText(this, "Kupljena pojedinačna ulaznica!", Toast.LENGTH_SHORT).show();
        } else if ("12345".equals(promo_text)) {
            Intent intent = new Intent(this, PackageActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
            Toast.makeText(this, "Kupljena pojedinačna ulaznica sa promo kodom!", Toast.LENGTH_SHORT).show();
        } else {
            promo.setText("");
            Toast.makeText(this, "Pogrešan promo kod!", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Packet> getPackets() {
        List<Packet> packetList = Util.getPackets(this);
        return packetList;
    }

    private void loadDataFromList() {
        Packet packet = packetList.get(index);
        TextView title = findViewById(R.id.ticket_title);
        TextView description = findViewById(R.id.ticket_description);
        ImageView image = findViewById(R.id.ticket_image);
        EditText promo = findViewById(R.id.ticket_promo);
        Button button = findViewById(R.id.ticket_button);

        String titleStart = "Pojedinačna ulaznica za ";
        String packet_title = titleStart + packet.getName();
        title.setText(packet_title);
        description.setText(packet.getDescription());
        image.setImageResource(packet.getImage());
        if (index < colorMap.size()) {
            promo.setBackgroundColor(ContextCompat.getColor(this, colorMap.get(index)));
            button.setBackgroundColor(ContextCompat.getColor(this, colorMap.get(index)));
        }
    }
}
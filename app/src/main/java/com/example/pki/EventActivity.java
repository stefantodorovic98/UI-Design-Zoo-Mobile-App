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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pki.models.Event;

import java.util.List;

public class EventActivity extends AppCompatActivity {

    private List<Event> eventList;
    private int index = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar toolbar = findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView textView = findViewById(R.id.back_toolbar_text);
        textView.setText("Pregled dešavanja");

        eventList = getEvents();

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
            intent.putExtra("index", 0);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void increaseEventLikeNumber(int number) {
        eventList.get(index).setLikes(number);
        Util.setEvents(this, eventList);
    }

    private List<Event> getEvents() {
        List<Event> eventList = Util.getEvents(this);
        return eventList;
    }

    public void onClickLeft(View view) {
        if (index == 0) {
            index = 4;
        } else index--;
        loadDataFromList();
    }

    public void onClickRight(View view) {
        if (index == 4) {
            index = 0;

        } else index++;
        loadDataFromList();
    }

    public void onClickLike(View view) {
        TextView likes = findViewById(R.id.likes);
        String likes_text = likes.getText().toString();
        int likes_number = Integer.parseInt(likes_text) + 1;
        String likes_number_text = likes_number + "";
        likes.setText(likes_number_text);
        increaseEventLikeNumber(likes_number);
    }

    private void loadDataFromList() {
        ImageView image = findViewById(R.id.event_image);
        TextView name = findViewById(R.id.event_name);
        TextView description = findViewById(R.id.event_description);
        TextView likes = findViewById(R.id.likes);

        image.setImageResource(eventList.get(index).getImage());
        name.setText(eventList.get(index).getName());
        description.setText(eventList.get(index).getDescription());
        String likes_number_text = eventList.get(index).getLikes() + "";
        likes.setText(likes_number_text);
    }
}
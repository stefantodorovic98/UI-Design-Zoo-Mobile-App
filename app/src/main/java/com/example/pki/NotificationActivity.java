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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pki.models.Notification;

import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;

    private List<Notification> notificationList;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView textView = findViewById(R.id.back_toolbar_text);
        textView.setText("Obaveštenja");

        notificationList = getNotifications();

        ListView listView = findViewById(R.id.notificationListView);

        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
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

    private void setNotifications() {
        Util.setNotifications(this, notificationList);
    }

    private List<Notification> getNotifications() {
        List<Notification> notificationList = Util.getNotifications(this);
        return notificationList;
    }

    public void deleteNotification(View view) {
        ListView listView = findViewById(R.id.notificationListView);
        int i = listView.getPositionForView(view);
        notificationList.remove(i);
        customAdapter.notifyDataSetChanged();
        setNotifications();
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return notificationList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.notification_row_data, null);
            TextView message = view1.findViewById(R.id.firstLine);
            TextView date = view1.findViewById(R.id.secondLine);

            message.setText(notificationList.get(i).getMessage());
            date.setText(notificationList.get(i).getDate());

            return view1;
        }
    }
}
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pki.models.Animal;

import java.util.List;

public class AnimalListActivity extends AppCompatActivity {

    private List<Animal> animalList;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        Toolbar toolbar = findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView textView = findViewById(R.id.back_toolbar_text);
        textView.setText("Pregled životinja");

        animalList = getAnimals();

        ListView listView = findViewById(R.id.animalListView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), AnimalInfoActivity.class);
            intent.putExtra("index", i);
            startActivity(intent);
        });
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

    private List<Animal> getAnimals() {
        List<Animal> animalList = Util.getAnimals(this);
        return animalList;
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return animalList.size();
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
            View view1 = getLayoutInflater().inflate(R.layout.animal_row_data, null);
            TextView animal = view1.findViewById(R.id.animal_name);
            ImageView image = view1.findViewById(R.id.animal_image);

            animal.setText(animalList.get(i).getName());
            image.setImageResource(animalList.get(i).getImage());

            return view1;
        }
    }

}
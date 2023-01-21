package com.example.pki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pki.models.Animal;
import com.example.pki.models.Comment;

import java.util.List;

public class AnimalInfoActivity extends AppCompatActivity {

    private Animal animal;
    private int index = 0;

    List<Comment> commentList;

    CommentsAdapter commentsAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);

        Toolbar toolbar = findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView textView = findViewById(R.id.back_toolbar_text);
        textView.setText("Pregled životinja");

        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);

        animal = getAnimal();

        TextView name = findViewById(R.id.animal_name);
        ImageView image = findViewById(R.id.animal_image);
        TextView description = findViewById(R.id.animal_description);

        name.setText(animal.getName());
        image.setImageResource(animal.getImage());
        description.setText(animal.getDescription());

        commentList = animal.getCommentList();

        RecyclerView recycleViewComment = findViewById(R.id.commentRecyclerView);

        commentsAdapter = new CommentsAdapter(commentList);
        recycleViewComment.setAdapter(commentsAdapter);
        recycleViewComment.setLayoutManager(new LinearLayoutManager(this));
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
            Intent intent = new Intent(this, AnimalListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onCommentAdd(View view) {
        TextView user_name = findViewById(R.id.user_name);
        TextView user_comment = findViewById(R.id.user_comment);

        String user_name_text = user_name.getText().toString();
        String user_comment_text = user_comment.getText().toString();

        if ("".equals(user_name_text) || "".equals(user_comment_text)) {
            Toast.makeText(this, "Morate popuniti oba polja!", Toast.LENGTH_SHORT).show();
        } else {
            Comment new_comment = new Comment(user_name_text, user_comment_text);
            commentList.add(new_comment);
            animal.setCommentList(commentList);
            commentsAdapter.notifyDataSetChanged();
            setAnimals();
        }
    }

    private void setAnimals() {
        List<Animal> animalList = Util.getAnimals(this);
        animalList.set(index, animal);
        Util.setAnimals(this, animalList);
    }

    private Animal getAnimal() {
        Animal animal = Util.getAnimalForIndex(this, index);
        return animal;
    }

    private class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
        private List<Comment> commentList;

        public CommentsAdapter(List<Comment> commentList) {
            this.commentList = commentList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View commentView = inflater.inflate(R.layout.comment_row_data, parent, false);

            ViewHolder viewHolder = new ViewHolder(commentView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Comment comm = commentList.get(position);

            TextView name = holder.name;
            name.setText(comm.getName());
            TextView comment = holder.comment;
            comment.setText(comm.getComment());
        }

        @Override
        public int getItemCount() {
            return commentList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView name;
            public TextView comment;

            public ViewHolder(View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
                comment = itemView.findViewById(R.id.comment);
            }
        }
    }
}
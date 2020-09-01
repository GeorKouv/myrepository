package com.example.songapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    TextView addLabel;
    EditText Name, Category, Artist, Rating, Critic, Duration, Link;


    Button addButton;
    Button clearButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addLabel = findViewById(R.id.addLabelId);
        Name = findViewById(R.id.NameId);
        Category = findViewById(R.id.CategoryId);
        Artist = findViewById(R.id.ArtistId);
        Rating = findViewById(R.id.RatingId);
        Critic = findViewById(R.id.CriticId);
        Duration = findViewById(R.id.DurationId);
        Link = findViewById(R.id.LinkId);

        addButton = findViewById(R.id.addButtonId);
        clearButton = findViewById(R.id.clearButtonId);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                myDB.addSong(Name.getText().toString().trim(), Category.getText().toString().trim(), Artist.getText().toString().trim(), Integer.valueOf(Rating.getText().toString().trim()),
                      Critic.getText().toString().trim(), Integer.parseInt(Duration.getText().toString().trim()), Link.getText().toString().trim());
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name.getText().clear();
                Category.getText().clear();
                Artist.getText().clear();
                Rating.getText().clear();
                Critic.getText().clear();
                Duration.getText().clear();
                Link.getText().clear();



            }
        });
    }
}
